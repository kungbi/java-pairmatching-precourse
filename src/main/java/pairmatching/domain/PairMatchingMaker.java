package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pairmatching.enums.Course;
import pairmatching.enums.Mission;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchRepository;

public class PairMatchingMaker {
    private final CrewRepository crewRepository;
    private final MatchRepository matchRepository;
    private final ShuffleUtil shuffleUtil;

    public PairMatchingMaker(CrewRepository crewRepository, MatchRepository matchRepository, ShuffleUtil shuffleUtil) {
        this.crewRepository = crewRepository;
        this.matchRepository = matchRepository;
        this.shuffleUtil = shuffleUtil;
    }

    public MatchGroups make(Course course, Mission mission) {
        if (this.matchRepository.isExist(course, mission)) {
            throw new IllegalStateException();
        }
        RandomCrewPairGenerator randomCrewPairGenerator = createCrewPairGenerator(course);

        List<Group> matches = new ArrayList<>();
        int retryCount = 0;
        while (randomCrewPairGenerator.getSize() != 0) {
            Set<String> crewNames = randomCrewPairGenerator.pollNextGroup();
            Set<CrewMember> crew = crewNames.stream()
                    .map(name -> this.crewRepository.findByCourseAndName(course, name)
                            .orElseThrow(IllegalStateException::new)).collect(Collectors.toSet());
            Group group = new Group(crew);

            if (this.matchRepository.hasSameGroupInLevel(course, mission.getLevel(), group)) {
                randomCrewPairGenerator.shuffle();
                retryCount++;
                if (retryCount == 3) {
                    throw new IllegalStateException("매칭이 불가능 합니다.");
                }
                continue;
            }
            matches.add(group);
            randomCrewPairGenerator.pop();
            retryCount = 0;
        }
        return new MatchGroups(
                matches,
                mission,
                course
        );
    }

    private RandomCrewPairGenerator createCrewPairGenerator(Course course) {
        List<CrewMember> crews = crewRepository.findAllByCourse(course);
        RandomCrewPairGenerator randomCrewPairGenerator = new RandomCrewPairGenerator(shuffleUtil,
                crews.stream().map(CrewMember::getName).toList());
        return randomCrewPairGenerator;
    }

    private CrewMember getCrew(List<CrewMember> crews) {
        Randoms.shuffle(crews);
        return crews.get(0);
    }
}

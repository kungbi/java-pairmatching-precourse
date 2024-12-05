package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.enums.Course;
import pairmatching.enums.Mission;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchRepository;

class PairMatchingMakerTest {


    static ShuffleUtil getShuffleUtil() {
        List<String> returns = new ArrayList<>(List.of("철수", "영희", "맹구", "짱구"));
        List<String> returns2 = new ArrayList<>(List.of("철수", "맹구", "영희", "짱구"));
        return new ShuffleUtil() {
            int tryCount = 0;

            @Override
            public List<String> shuffle(List<String> items) {
                tryCount++;
                if (tryCount == 2) {
                    return returns2;
                }
                return returns;
            }
        };
    }

    @Test
    void 페어_매칭_생성_테스트() {
        // given
        CrewRepository crewRepository = new CrewRepository();
        CrewMember 철수 = new CrewMember("철수", Course.BACKEND);
        CrewMember 영희 = new CrewMember("영희", Course.BACKEND);
        CrewMember 맹구 = new CrewMember("맹구", Course.BACKEND);
        CrewMember 짱구 = new CrewMember("짱구", Course.BACKEND);
        crewRepository.add(철수);
        crewRepository.add(영희);
        crewRepository.add(맹구);
        crewRepository.add(짱구);
        MatchRepository matchRepository = new MatchRepository();
        PairMatchingMaker pairMatchingMaker = new PairMatchingMaker(crewRepository, matchRepository,
                getShuffleUtil());

        MatchGroups matchGroups = pairMatchingMaker.make(Course.BACKEND, Mission.DEPLOYMENT);

        Assertions.assertThat(matchGroups.getMatches())
                .isEqualTo(List.of(new Group(Set.of(철수, 영희)), new Group(Set.of(맹구, 짱구))));
    }

    @Test
    void 같은_레벨에서_이미_매칭된_상태() {
        // given
        CrewRepository crewRepository = new CrewRepository();
        CrewMember 철수 = new CrewMember("철수", Course.BACKEND);
        CrewMember 영희 = new CrewMember("영희", Course.BACKEND);
        CrewMember 맹구 = new CrewMember("맹구", Course.BACKEND);
        CrewMember 짱구 = new CrewMember("짱구", Course.BACKEND);
        crewRepository.add(철수);
        crewRepository.add(영희);
        crewRepository.add(맹구);
        crewRepository.add(짱구);
        MatchRepository matchRepository = new MatchRepository();
        matchRepository.add(new MatchGroups(
                List.of(new Group(Set.of(철수, 영희))),
                Mission.PERFORMANCE_IMPROVEMENT,
                Course.BACKEND
        ));
        PairMatchingMaker pairMatchingMaker = new PairMatchingMaker(crewRepository, matchRepository,
                getShuffleUtil());

        MatchGroups matchGroups = pairMatchingMaker.make(Course.BACKEND, Mission.DEPLOYMENT);

        Assertions.assertThat(matchGroups.getMatches())
                .isEqualTo(List.of(new Group(Set.of(철수, 맹구)), new Group(Set.of(영희, 짱구))));
    }

}
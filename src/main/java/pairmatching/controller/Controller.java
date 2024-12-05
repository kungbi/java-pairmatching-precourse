package pairmatching.controller;

import java.util.Optional;
import pairmatching.domain.MatchGroups;
import pairmatching.domain.PairMatchingMaker;
import pairmatching.dtos.MatchingResultDto;
import pairmatching.dtos.PairMatchInputDto;
import pairmatching.enums.Course;
import pairmatching.enums.GeneralCommand;
import pairmatching.enums.Mission;
import pairmatching.enums.RematchCommand;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchRepository;
import pairmatching.view.OutputView;

public class Controller {
    private final CrewRepository crewRepository;
    private final MatchRepository matchRepository;
    private final PairMatchingMaker pairMatchingMaker;

    public Controller(CrewRepository crewRepository, MatchRepository matchRepository,
                      PairMatchingMaker pairMatchingMaker) {
        this.crewRepository = crewRepository;
        this.matchRepository = matchRepository;
        this.pairMatchingMaker = pairMatchingMaker;
    }

    public void run() {
        while (true) {
            GeneralCommand command = RetryInputUtil.getCommand();
            if (command == GeneralCommand.QUIT) {
                break;
            }
            if (command == GeneralCommand.MAKE_PAIR) {
                while (true) {
                    PairMatchInputDto pairMatchInput = RetryInputUtil.getPairMatchInput();
                    boolean result = makePair(pairMatchInput.course(), pairMatchInput.mission());
                    if (result) {
                        break;
                    }
                }
            }
            if (command == GeneralCommand.RETRIEVE_PAIR) {
                PairMatchInputDto pairMatchInput = RetryInputUtil.getPairMatchInput();
                retrievePair(pairMatchInput.course(), pairMatchInput.mission());
            }
            if (command == GeneralCommand.RESET_PAIR) {
                this.matchRepository.reset();
                OutputView.printResetMessage();
            }
        }
    }

    private void retrievePair(Course course, Mission mission) {
        Optional<MatchGroups> foundMatchGroups = this.matchRepository.findByCourseAndMission(course, mission);
        if (foundMatchGroups.isEmpty()) {
            OutputView.printError("매칭 이력이 없습니다.");
            return;
        }
        MatchGroups matchGroups = foundMatchGroups.get();
        OutputView.printMatchingResult(MatchingResultDto.from(matchGroups));
    }

    private boolean makePair(Course course, Mission mission) {
        while (true) {
            Optional<MatchGroups> foundMatchGroups = this.matchRepository.findByCourseAndMission(course, mission);
            if (foundMatchGroups.isPresent()) {
                RematchCommand rematch = RetryInputUtil.getRematch();
                if (rematch == RematchCommand.NO) {
                    return false;
                }
                this.matchRepository.resetByCourseAndMission(course, mission);
            }
            MatchGroups matchGroups = pairMatchingMaker.make(course, mission);
            this.matchRepository.add(matchGroups);
            OutputView.printMatchingResult(MatchingResultDto.from(matchGroups));
            break;
        }
        return true;
    }

}

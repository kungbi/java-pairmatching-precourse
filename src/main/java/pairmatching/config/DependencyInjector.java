package pairmatching.config;


import pairmatching.controller.Controller;
import pairmatching.domain.PairMatchingMaker;
import pairmatching.domain.RandomShuffleUtil;
import pairmatching.domain.ShuffleUtil;
import pairmatching.file.parser.CrewParser;
import pairmatching.file.reader.CsvReader;
import pairmatching.initializer.BackendCrewInitializer;
import pairmatching.initializer.FrontendCrewInitializer;
import pairmatching.repository.CrewRepository;
import pairmatching.repository.MatchRepository;

public class DependencyInjector {
    private static CrewRepository crewRepository;
    private static MatchRepository matchRepository;
    private static ShuffleUtil shuffleUtil;
    private static PairMatchingMaker pairMatchingMaker;
    private static Controller controller;

    public Controller getController() {
        if (controller == null) {
            controller = new Controller(getMatchRepository(), getPairMatchingMaker());
        }
        return controller;
    }

    private CrewRepository getCrewRepository() {
        if (crewRepository == null) {
            crewRepository = new CrewRepository();
            new BackendCrewInitializer(crewRepository, new CrewParser(CsvReader.of("backend-crew.md", false))).init();
            new FrontendCrewInitializer(crewRepository, new CrewParser(CsvReader.of("frontend-crew.md", false))).init();
        }
        return crewRepository;
    }

    private MatchRepository getMatchRepository() {
        if (matchRepository == null) {
            matchRepository = new MatchRepository();
        }
        return matchRepository;
    }

    private ShuffleUtil getShuffleUtil() {
        if (shuffleUtil == null) {
            shuffleUtil = new RandomShuffleUtil();
        }
        return shuffleUtil;
    }

    private PairMatchingMaker getPairMatchingMaker() {
        if (pairMatchingMaker == null) {
            pairMatchingMaker = new PairMatchingMaker(getCrewRepository(), getMatchRepository(), getShuffleUtil());
        }
        return pairMatchingMaker;
    }
}

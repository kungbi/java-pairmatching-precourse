package pairmatching.initializer;

import org.junit.jupiter.api.Test;
import pairmatching.file.parser.CrewParser;
import pairmatching.file.reader.CsvReader;
import pairmatching.repository.CrewRepository;

public class InitializerTest {
    @Test
    void CrewRepository_초기화_테스트() {
        CrewRepository crewRepository = new CrewRepository();

        BackendCrewInitializer backendCrewInitializer = new BackendCrewInitializer(crewRepository,
                new CrewParser(CsvReader.of("backend-crew.md", false)));
        FrontendCrewInitializer frontendCrewInitializer = new FrontendCrewInitializer(crewRepository,
                new CrewParser(CsvReader.of("frontend-crew.md", false)));

        backendCrewInitializer.init();
        frontendCrewInitializer.init();

        System.out.println(crewRepository.findAll());
    }
}

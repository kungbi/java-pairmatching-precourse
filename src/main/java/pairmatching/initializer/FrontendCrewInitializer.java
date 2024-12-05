package pairmatching.initializer;

import pairmatching.domain.Crew;
import pairmatching.enums.Course;
import pairmatching.file.field.CrewField;
import pairmatching.file.parser.GenericParser;
import pairmatching.repository.Repository;

public class FrontendCrewInitializer extends AbstractInitializer<Crew, CrewField> {

    public FrontendCrewInitializer(Repository<Crew> repository, GenericParser<CrewField> parser) {
        super(repository, parser);
    }

    @Override
    protected Crew mapToDomain(CrewField field) {
        return new Crew(field.name(), Course.FRONTEND);
    }
}

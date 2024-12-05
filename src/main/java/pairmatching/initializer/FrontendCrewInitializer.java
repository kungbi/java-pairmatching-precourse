package pairmatching.initializer;

import pairmatching.domain.CrewMember;
import pairmatching.enums.Course;
import pairmatching.file.field.CrewField;
import pairmatching.file.parser.GenericParser;
import pairmatching.repository.Repository;

public class FrontendCrewInitializer extends AbstractInitializer<CrewMember, CrewField> {

    public FrontendCrewInitializer(Repository<CrewMember> repository, GenericParser<CrewField> parser) {
        super(repository, parser);
    }

    @Override
    protected CrewMember mapToDomain(CrewField field) {
        return new CrewMember(field.name(), Course.FRONTEND);
    }
}

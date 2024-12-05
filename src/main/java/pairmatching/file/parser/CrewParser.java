package pairmatching.file.parser;


import pairmatching.file.field.CrewField;
import pairmatching.file.reader.Reader;

public class CrewParser extends AbstractFieldParser<CrewField> {

    public CrewParser(Reader reader) {
        super(reader, CrewField.class);
    }

}

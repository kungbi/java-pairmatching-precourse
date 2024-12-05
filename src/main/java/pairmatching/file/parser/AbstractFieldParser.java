package pairmatching.file.parser;


import java.io.IOException;
import java.util.List;
import pairmatching.file.mapper.GenericObjectMapper;
import pairmatching.file.reader.Reader;

public abstract class AbstractFieldParser<T> implements GenericParser<T> {

    private final Reader reader;
    private final GenericObjectMapper<T> mapper;

    protected AbstractFieldParser(Reader reader, Class<T> targetClass) {
        this.reader = reader;
        this.mapper = new GenericObjectMapper<>(targetClass);
    }

    @Override
    public T nextLine() throws IOException {
        if (reader == null) {
            throw new NullPointerException("reader is null");
        }
        List<String> fields = reader.readLine();
        if (fields == null || fields.isEmpty()) {
            reader.close();
            return null;
        }
        return mapper.map(fields);
    }
}

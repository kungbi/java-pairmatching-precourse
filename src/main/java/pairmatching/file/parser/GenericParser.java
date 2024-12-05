package pairmatching.file.parser;

import java.io.IOException;

public interface GenericParser<T> {
    T nextLine() throws IOException;
}

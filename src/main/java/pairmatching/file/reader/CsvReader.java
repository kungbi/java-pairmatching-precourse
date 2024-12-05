package pairmatching.file.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class CsvReader implements Reader {
    private final BufferedReader reader;

    public CsvReader(BufferedReader reader, boolean header) {
        if (reader == null) {
            throw new IllegalArgumentException("reader is null");
        }
        this.reader = reader;
        if (header) {
            skipHeader();
        }
    }

    @Override
    public List<String> readLine() throws IOException {
        String line = this.reader.readLine();
        if (line == null) {
            return null;
        }

        return List.of(line.split(","));
    }

    @Override
    public void close() throws IOException {
        this.reader.close();
    }


    private void skipHeader() {
        try {
            this.reader.readLine();
        } catch (IOException error) {
            throw new IllegalArgumentException(error);
        }
    }

    // static factory method - read from resources directory
    public static CsvReader of(String filename, boolean header) {
        return new CsvReader(new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(CsvReader.class.getClassLoader().getResourceAsStream(filename)))), header);
    }
}
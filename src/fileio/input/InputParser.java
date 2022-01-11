package fileio.input;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Class used for parsing the input
 */
public final class InputParser {

    private InputParser() {
    }

    /**
     * Parses the input and returns it in an InputFormat object
     * @param inputFile a File object representing the input file
     * @return an InputFormat object containing the parsed input
     */
    public static InputFormat parseInput(final File inputFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // deserialize the input using Jackson
            InputFormat inputFormat = objectMapper.readValue(inputFile, InputFormat.class);
            return inputFormat;

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * Creates a File object representing the file from the path given as parameter
     * @param inputFilePath string containing the file path
     * @return File object
     */
    public static File getInputFile(final String inputFilePath) {
        File inputFile = Paths.get(inputFilePath).toFile();

        return inputFile;
    }
}

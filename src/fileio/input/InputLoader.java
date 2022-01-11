package fileio.input;

import java.io.File;
import java.io.IOException;

/**
 * Class which handles the input reading
 */
public final class InputLoader {

    private InputLoader() {
    }

    /**
     * Reads the input data in an OutputFormat object and returns it
     * @param inputFilePath string containing the path to the input file
     * @return InputFormat object containing the read data
     */
    public static InputFormat readInputData(final String inputFilePath) throws IOException {
        // get the file from a file path
        File inputFile = InputParser.getInputFile(inputFilePath);

        // parse and return the input under the form of InputFormat
        return InputParser.parseInput(inputFile);
    }

    /**
     * Extracts the data from an InputFormat object into an InputData object and returns it
     * @param inputFormat the InputFormat object from where the data needs to be extracted
     * @return InputData object containing the data extracted from the InputFormat
     */
    public static InputData loadInputData(final InputFormat inputFormat) {
        return new InputData(inputFormat);
    }
}

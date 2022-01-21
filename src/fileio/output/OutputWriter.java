package fileio.output;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import database.Database;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class which handles the output file writing
 */
public final class OutputWriter {

    private OutputWriter() {
    }

    /**
     * Writes the output in the file given as parameter
     * @param outputFilePath a string containing the path to the output file
     */
    public static void writeOutput(final String outputFilePath) throws IOException,
            JsonProcessingException {
        // get the output data from the database
        OutputData outputData = Database.getInstance().getOutputData();

        // put the output data into a format used for Jackson writing
        OutputFormat outputFormat = new OutputFormat(outputData);

        // string used for storing the output in JSON format
        String jsonOutputString = "";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // serialize to JSON and store the result
            jsonOutputString = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(outputFormat);

        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

        try {
            // write the output string to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write(jsonOutputString);
            writer.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Creates the output directory in the current folder
     */
    public static void createOutputDirectory() throws IOException {
        File outputDirectory = new File(Constants.OUTPUT_FOLDER);
        if (outputDirectory.exists()) {
            return;
        }
        if (outputDirectory.mkdir()) {
            return;
        }
        throw new IOException("Failed to create output directory");
    }

}

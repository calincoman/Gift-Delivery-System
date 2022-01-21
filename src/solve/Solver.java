package solve;

import common.Constants;

import database.Database;
import database.DatabaseLoader;
import database.DatabaseUpdate;

import enums.GiftStrategyType;

import fileio.input.InputData;
import fileio.input.InputFormat;
import fileio.input.InputLoader;

import fileio.output.OutputData;
import fileio.output.OutputWriter;

import visitor.ElvesOperations;

import java.io.IOException;

/**
 * Class which executes the simulation and runs the main flow of the program
 */
public final class Solver {

    private Solver() {
    }

    /**
     * This is the main method which reads the input, initiates the simulation and prints
     * the output
     */
    public static void execute() throws IOException {

        // create the output directory where to put the result json files
        OutputWriter.createOutputDirectory();

        // iterate through the tests
        for (Integer fileIndex = 1; fileIndex <= Constants.TESTS_NUMBER; ++fileIndex) {

            // build the string containing the path to the input file
            String inputFilePath = Constants.INPUT_PATH + fileIndex.toString()
                    + Constants.FILE_EXTENSION;

            // read the input with Jackson library in an input format
            InputFormat inputFormat = InputLoader.readInputData(inputFilePath);

            // get the data from the input format
            InputData inputData = InputLoader.loadInputData(inputFormat);

            // load the data in the database
            DatabaseLoader.loadDatabase(inputData);

            // reset year counter
            YearCounter.getInstance().setYear(Constants.FIRST_YEAR);

            // simulate the first round (round 0) and the rounds from the next years
            Solver.simulateRoundZero();
            Solver.simulateNextYears();

            // build the string containing the path to the output file
            String outputFilePath = Constants.OUTPUT_PATH + fileIndex.toString()
                    + Constants.FILE_EXTENSION;

            // write the output
            OutputWriter.writeOutput(outputFilePath);

            // unload the data from the database
            DatabaseLoader.unLoadDatabase();
        }
    }

    /**
     * Runs the first round of the simulation
     */
    public static void simulateRoundZero() {
        // first eliminate all young adults from the children list
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();
        databaseUpdate.eliminateYoungAdults();

        // calculate the budget for each child and store them in the database
        Database.getInstance().setChildBudgets(Calculator.getChildBudgets());

        // apply the black and pink elves to modify the budget
        ElvesOperations.sendBudgetElves();

        // initialize (declare empty lists) the objects used for output
        // there is year 0 too, so the total number of years is numberOfYears + 1
        Database.getInstance().setOutputData(new OutputData(Database.getInstance()
                .getNumberOfYears() + 1));

        // in the first round the gift assigning strategy is by default by child ID
        Database.getInstance().setGiftStrategy(GiftStrategyType.ID);

        // build and load in the database the child output data for the current year
        OutputDataBuilder.buildOutput();
        // assign gifts in the current year
        OutputDataBuilder.assignGifts();

        // apply the yellow elves to assign new gifts to some children
        ElvesOperations.sendGiftElves();
    }

    /**
     * Runs the rounds from the next years of the simulation
     */
    public static void simulateNextYears() {
        int numberOfYears = Database.getInstance().getNumberOfYears();

        // object used for updating the data from the database
        DatabaseUpdate databaseUpdate = new DatabaseUpdate();

        // simulate next years
        for (int year = 1; year <= numberOfYears; ++year) {

            // increment year
            YearCounter.getInstance().increaseYear();

            // update the database using the annual change from the current year
            databaseUpdate.updateAllData();

            // calculate the budget for each child and store them in the database
            Database.getInstance().setChildBudgets(Calculator.getChildBudgets());

            // apply the black and pink elves to modify the budget
            ElvesOperations.sendBudgetElves();

            // build and load in the database the child output data for the current year
            OutputDataBuilder.buildOutput();

            // assign gifts in the current year
            OutputDataBuilder.assignGifts();

            // apply the yellow elves to assign new gifts to some children
            ElvesOperations.sendGiftElves();
        }
    }
}

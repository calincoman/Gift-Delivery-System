package solve;

import common.Constants;
import common.Utils;
import database.Database;
import database.DatabaseLoader;
import database.DatabaseUpdate;
import distribution.shipment.Gift;
import factory.ScoreStrategyFactory;
import fileio.input.InputData;
import fileio.input.InputFormat;
import fileio.input.InputLoader;
import fileio.output.ChildOutputData;
import fileio.output.OutputData;
import fileio.output.OutputWriter;
import status.change.AnnualChange;
import status.update.ChildUpdate;
import strategy.delivery.GiftAssigner;

import java.io.IOException;
import java.util.ArrayList;

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
     * Loads in the database the output data (the child output data) for a year
     * given as parameter
     * @param year the year mentioned above
     */
    public static void loadChildOutputDataForYear(final Integer year) {

        Database.getInstance().getChildren().forEach(child -> {
                    // access the ArrayList from the year index
                    Database.getInstance().getOutputData().getAnnualChildren().get(year)
                            // add a new child output data created using builder pattern
                    .add(new ChildOutputData.Builder(child)
                            // add the average score calculated with the strategy corresponding to
                            // the child's age category
                            .withAverageScore(ScoreStrategyFactory.getScoreStrategy(
                                    Utils.getScoreStrategyType(child)).getAverageScore(child))
                            // add the nice scores of the child
                            .withNiceScoreHistory(new ArrayList<Double>(child.getNiceScores()))
                            // add the assigned budget
                            .withAssignedBudget(Database.getInstance().getChildBudgets()
                                    .get(child))
                            // assign the gifts to the child
                            .withReceivedGifts(new ArrayList<Gift>(GiftAssigner
                                    .assignGiftsToChild(child)))
                            .build());
        });
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

        // initialize (declare empty lists) the objects used for output
        // there is year 0 too, so the total number of years is numberOfYears + 1
        Database.getInstance().setOutputData(new OutputData(Database.getInstance()
                .getNumberOfYears() + 1));

        // load in the database the child output data for the first year
        loadChildOutputDataForYear(0);
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
            // the annual changes list is indexed from 0, so get the element from year - 1 position
            AnnualChange annualChange = Database.getInstance().getAnnualChanges().get(year - 1);
            // increase age of all children by 1
            databaseUpdate.increaseAge();
            // eliminate children that have become young adults
            databaseUpdate.eliminateYoungAdults();

            // do the updates
            databaseUpdate.updateSantaBudget(annualChange.getNewSantaBudget());
            databaseUpdate.addNewGifts(annualChange.getNewGifts());
            databaseUpdate.addNewChildren(annualChange.getNewChildren());
            databaseUpdate.updateChildren(new ArrayList<ChildUpdate>(annualChange
                    .getChildrenUpdates()));

            // calculate the budget for each child and store them in the database
            Database.getInstance().setChildBudgets(Calculator.getChildBudgets());

            // calculate the output data for a child in a year and store it in the database
            loadChildOutputDataForYear(year);
        }
    }
}

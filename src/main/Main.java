package main;

import checker.Checker;
import solve.Solver;

import java.io.IOException;

/**
 * Class used to run the code
 */
public final class Main {

    private Main() {
        ///constructor for checkstyle
    }
    /**
     * This method is used to call the checker which calculates the score
     * Main entry point of the program
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) throws IOException {
        // call the solver to execute the simulation
        Solver.execute();
        // calculate the score
        Checker.calculateScore();
    }
}

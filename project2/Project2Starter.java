import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project2Starter {

    private static final int MAX_ROWS = 20;

    public static void main(String[] args) throws FileNotFoundException {
        // TODO Load data from file specified in program arguments if it exists

        // CLI Loop
        Scanner in = new Scanner(System.in);
        while (true) {
            // Print options
            System.out.println("==================================================");
            System.out.println("*                    Main Menu                   *");
            System.out.println("==================================================");
            // TODO Implement user selection
            break;
        }
    }

    /**
     * <b>This method calculates the values shared between the Aggregate and Individual Sales Reports.</b>
     */
    private static void calcSharedValues() {
        System.out.println("DEBUG ! Calculate shared values");
        // TODO Implement caldSharedValues()
        
    }

    /**
     * <b>Calculates value(s) specific to the Aggregate Sales report and prints the results to the screen.</b>
     */
    private static void viewAggregateReport() {
        System.out.println("DEBUG ! Viewing Aggregate Sales Report...");
        // TODO Perform aggregate report calculation(s)

        // TODO Print aggregate report

    }

    /**
     * <b>Prints the Individual Sales Report to the screen.</b>
     */
    private static void viewIndividualReport() {
        System.out.println("DEBUG ! Viewing Individual Report...");
        // TODO Print individual report

    }

    /**
     * <b> Prompts the user to enter the name of a file, then calls {@link #loadDataFromFile(File)} to load the data
     * into memory.</b>
     *
     * @throws FileNotFoundException
     */
    private static void loadNewData() throws FileNotFoundException {
        System.out.println("DEBUG ! Loading new data...");
        // TODO Write the code to load new data.

    }

    /**
     * Loads data from {@code file} into global array(s).
     *
     * @param file
     * @throws FileNotFoundException
     */
    private static void loadDataFromFile(File file) throws FileNotFoundException {
        System.out.println("DEBUG ! Loading data from " + file.getName() + "...");
        // TODO Write the code to load data from a file into memory.

    }

    private static void hDiv() {
        System.out.println("--------------------------------------------------");
    }
}
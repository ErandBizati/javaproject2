import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class SaleRecord {
    String name;
    int smalls;
    int larges;
    int combos;

    public SaleRecord(String name, int smalls, int larges, int combos) {
        this.name = name;
        this.smalls = smalls;
        this.larges = larges;
        this.combos = combos;
    }

    public String toString() {
        return String.format("%s,%d,%d,%d", name, smalls, larges, combos);
    }
}


public class Project2Starter {
    private static final int MAX_ROWS = 20;
    private static List<SaleRecord> salesData = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            File initialFile = new File(args[0]);
            if (initialFile.exists()) {
                loadDataFromFile(initialFile);
            } else {
                System.out.println("File not found: " + args[0]);
            }
        }
    
        // CLI Loop
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println("==================================================");
            System.out.println("*                    Main Menu                   *");
            System.out.println("==================================================");
            System.out.println("1. Load New File");
            System.out.println("2. View Individual Sales");
            System.out.println("3. View Aggregate Report");
            System.out.println("4. Quit");
            System.out.print("Select an option: ");
        
            if (!in.hasNextInt()) {
                System.out.println("Invalid selection. Please enter a number.");
                in.next(); // Consume the invalid input
                continue;
            }
        
            int selection = in.nextInt();
            in.nextLine(); // Consume newline
            
            switch (selection) {
                case 1:
                    loadNewData();
                    break;
                case 2:
                    viewIndividualReport();
                    break;
                case 3:
                    viewAggregateReport();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    in.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }
    }

    /**
     * <b>This method calculates the values shared between the Aggregate and Individual Sales Reports.</b>
     */
    private static void calcSharedValues() {
        System.out.println("DEBUG ! Calculate shared values");
        // TODO Implement calcSharedValues()
        
    }

    /**
     * <b>Calculates value(s) specific to the Aggregate Sales report and prints the results to the screen.</b>
     */
    private static void viewAggregateReport() {
        if (salesData.isEmpty()) {
            System.out.println("No data available. Please load a sales file first.");
            return;
        }
    
        int totalSmalls = 0, totalLarges = 0, totalCombos = 0;
        float totalSales = 0;
        for (SaleRecord record : salesData) {
            totalSmalls += record.smalls;
            totalLarges += record.larges;
            totalCombos += record.combos;
            totalSales += (record.smalls * 7.5f) + (record.larges * 10.5f) + (record.combos * 11.5f);
        }
        float clubFunds = totalSales * 0.2f;
        float marksAmount = totalSales * 0.8f;
    
        System.out.println("Aggregate Sales Report:");
        System.out.printf("%-28s %d\n", "Total Small Vouchers Sold:", totalSmalls);
        System.out.printf("%-28s %d\n", "Total Large Vouchers Sold:", totalLarges);
        System.out.printf("%-28s %d\n", "Total Combos Sold:", totalCombos);
        System.out.printf("%-28s $%.2f\n", "Total Sales:", totalSales);
        System.out.printf("%-28s $%.2f\n", "Funds Raised for Club:", clubFunds);
        System.out.printf("%-28s $%.2f\n", "Amount to Pay Mark's:", marksAmount);
        
        // Calculate average sales per member
        float averageSales = totalSales / salesData.size();
        System.out.printf("%-28s $%.2f\n", "Average Sales per Member:", averageSales);
    
        // Identify the top earners
        SaleRecord topEarner = salesData.get(0); // Assume the first one is the top earner initially
        for (SaleRecord record : salesData) {
            float recordTotalSales = (record.smalls * 7.5f) + (record.larges * 10.5f) + (record.combos * 11.5f);
            if (recordTotalSales > (topEarner.smalls * 7.5f) + (topEarner.larges * 10.5f) + (topEarner.combos * 11.5f)) {
                topEarner = record;
            }
        }
        System.out.printf("%-28s %s\n", "Top Earner:", topEarner.name);
    }

    /**
     * <b>Prints the Individual Sales Report to the screen.</b>
     */
    private static void viewIndividualReport() {
        if (salesData.isEmpty()) {
            System.out.println("No data available. Please load a sales file first.");
            return;
        }
    
        System.out.println("Individual Sales Report:");
        System.out.println("Name\tSmall\tLarge\tCombo\tTotal Sales");
        for (SaleRecord record : salesData) {
            float totalSales = (record.smalls * 7.5f) + (record.larges * 10.5f) + (record.combos * 11.5f);
            System.out.printf("%s\t%d\t%d\t%d\t$%.2f\n", record.name, record.smalls, record.larges, record.combos, totalSales);
        }
        //extra credit goes here
    }
    

    /**
     * <b> Prompts the user to enter the name of a file, then calls {@link #loadDataFromFile(File)} to load the data
     * into memory.</b>
     *
     * @throws FileNotFoundException
     */
    private static void loadNewData() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the name of the file to load:");
        String fileName = in.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            loadDataFromFile(file);
        } else {
            System.out.println("File not found: " + fileName);
        }
    }

    /**
     * Loads data from {@code file} into global array(s).
     *
     * @param file
     * @throws FileNotFoundException
     */
    private static void loadDataFromFile(File file) throws FileNotFoundException {
        salesData.clear(); // Clear existing data to ensure it's ready for new data
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            // Split the line into parts based on spaces
            String[] parts = line.split("\\s+");
            if (parts.length >= 4) { // Ensure there's at least one name part and three numbers
                try {
                    // Reconstruct the name from all parts except the last three
                    String name = String.join(" ", Arrays.copyOfRange(parts, 0, parts.length - 3));
                    int smalls = Integer.parseInt(parts[parts.length - 3]);
                    int larges = Integer.parseInt(parts[parts.length - 2]);
                    int combos = Integer.parseInt(parts[parts.length - 1]);
                    salesData.add(new SaleRecord(name, smalls, larges, combos));
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing numbers in line: " + line);
                }
            } else {
                System.out.println("Incorrect format in line: " + line);
            }
        }
        fileScanner.close();
        // Optional: print the number of records loaded to verify
        System.out.println(salesData.size() + " records loaded successfully from " + file.getName());
    }

    private static void hDiv() {
        System.out.println("--------------------------------------------------");
    }
}


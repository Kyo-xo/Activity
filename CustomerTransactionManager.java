import java.util.Scanner;
public class CustomerTransactionManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- Task 1: Dynamic Data Input via Scanner ---
        // Ask the use how many transactions they want to input dynamically.
        System.out.print("Enter number of customer transactions to process: ");
        int count = scanner.nextInt();
        scanner.nextLine(); // Clear input buffer (consume the leftover newline character)

        // --- Task 2: Array Implementations ---
        // Parallel arrays are used: data at the same index 'i' belongs to the same customer.
        String[] transactionIds = new String[count]; // Stores Transaction IDs
        double[] transactionAmounts = new double[count]; // Store Total Purchase Amounts
        double[][] itemDetails = new double[count][3]; // [Customer][Item Category 1-3]

        // Populate arrays interactively using a loop
        for (int i = 0; i < count; i++) {
            System.out.println("\n--- Entering Data for Customer " + (i + 1) + " ---");
            System.out.print("Enter Transaction ID: ");
            transactionIds[i] = scanner.nextLine();

            System.out.print("Enter Total Purchase Amount ($): ");
            transactionAmounts[i] = scanner.nextDouble();

            System.out.println("Enter breakdown for 3 item categories:");
            for (int j = 0; j < 3; j++) {
                System.out.print("  Category " + (j + 1) + " Amount: $");
                itemDetails[i][j] = scanner.nextDouble();
            }
            
            scanner.nextLine(); // Clear input buffer after reading numeric double values
        }

        // Display Raw Data Summary Table using Nested Loops (Task 2c)
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "RAW TRANSACTION DATA");

        // --- Task 3: Sorting Algorithm Implementation (Insertion Sort) ---
        // Prompt user to choose how they want to sort the data (Ascending or Descending). 
        System.out.print("\nWould you like to sort by amount in Ascending (ASC) or Descending (DESC)? ");
        String direction = scanner.next().trim().toUpperCase();
        
        // Call the insertion sort method, passing the parallel arrays and the chosen direction.
        insertionSort(transactionAmounts, transactionIds, itemDetails, direction);

        // Display Sorted Results based on user choice
        displayTransactionTable(transactionIds, transactionAmounts, itemDetails, "SORTED TRANSACTION DATA (" + direction + ")");

        scanner.close();
    }

    // Helper method to display tables (Task 2c)
    public static void displayTransactionTable(String[] ids, double[] amounts, double[][] details, String title) {
        System.out.println("\n========================================================");
        System.out.println("            " + title);
        System.out.println("========================================================");
        System.out.printf("%-15s %-15s %-25s\n", "Txn ID", "Total ($)", "Item Breakdown [1, 2, 3]");
        System.out.println("--------------------------------------------------------");
        
        // Loop through the arrays to print each customer's data row by row formatted nicely
        for (int i = 0; i < ids.length; i++) {
            System.out.printf("%-15s $%-14.2f [%.2f, %.2f, %.2f]\n", 
                ids[i], 
                amounts[i], 
                details[i][0], details[i][1], details[i][2]);
        }
        System.out.println("========================================================");
    }

    // --- Task 3: Insertion Sort Method with Parallel Array Tracking ---
    public static void insertionSort(double[] amounts, String[] ids, double[][] details, String order) {
        int n = amounts.length;

        // Outer loop: Starts from the second element (index 1) and assumes elements before it are sorted
        for (int i = 1; i < n; i++) {
            // Backup the current element's values into "key" variables so they aren't overwritten duting shifting
            double keyAmount = amounts[i];
            String keyId = ids[i];
            double[] keyRow = details[i];
            // 'j' represents the index of the last element in the sorted portion of the array
            int j = i - 1;

            // Determine condition based on sorting direction (ASC or DESC)
            boolean condition;
            do {
                // If user chose DESC (Descending: High to low)
                if (order.equals("DESC")) {
                    // Shift elements right if the sorted element is LESS than the key
                    condition = (j >= 0 && amounts[j] < keyAmount);
                }
                // Otherwise, default to ASC (Ascending: Low to High)
                else {
                    // Shift elements right if the sorted element is GREATER than the key
                    condition = (j >= 0 && amounts[j] > keyAmount);
                }

                // If the condition is met, shift data one positio to the right to make room
                if (condition) {
                    amounts[j + 1] = amounts[j];
                    ids[j + 1] = ids[j];
                    details[j + 1] = details[j];
                    j--; // Move pointer left to check the next element in the sorted segment
                }
            } while (condition); // Keep looping while elements need to be shifted

            // Insert key elements back into their correct sorted position found by the shifting process
            amounts[j + 1] = keyAmount;
            ids[j + 1] = keyId;
            details[j + 1] = keyRow;
        }
    }
}
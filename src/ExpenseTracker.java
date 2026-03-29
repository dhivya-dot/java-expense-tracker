import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {

    private static final String FILE_PATH = "data/expenses.csv";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Expense Tracker ---");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    System.out.println("Goodbye.");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void addExpense(Scanner sc) {

        try {

            System.out.print("Amount: ");
            double amount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Category: ");
            String category = sc.nextLine();

            System.out.print("Note: ");
            String note = sc.nextLine();

            String date = LocalDate.now().toString();

            FileWriter fw = new FileWriter(FILE_PATH,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(date + "," + amount + "," + category + "," + note);
            bw.newLine();

            bw.close();

            System.out.println("Expense added.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void viewExpenses() {

        try {

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                System.out.println("No expenses found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            System.out.println("\nDate | Amount | Category | Note");
            System.out.println("--------------------------------");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.println(
                        data[0] + " | " +
                                data[1] + " | " +
                                data[2] + " | " +
                                data[3]
                );
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
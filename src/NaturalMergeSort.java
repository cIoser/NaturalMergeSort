import java.util.Scanner;

public class NaturalMergeSort {
    public static void main(String[] args) {
        System.out.println("This program sorts people by BMI ascending (from lowest, does not mean healthiest.");
        System.out.println("1. Generate test records.");
        System.out.println("2. Enter records from keyboard.");

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        final int AUTOMATIC_DATA = 1;
        final int MANUAL_DATA = 2;

        Generator generator = new Generator();

        if (choice == AUTOMATIC_DATA || choice == MANUAL_DATA) {
            System.out.println("Enter number of records: ");
            int recordsCount = input.nextInt();
            generator.setRecordsCount(recordsCount);
        }

        if (choice == MANUAL_DATA) {
            generator.fromKeyboard();

        }
        else if (choice == AUTOMATIC_DATA) {
            generator.generateRandom();
        }
    }
}

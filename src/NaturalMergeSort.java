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

        if (choice == AUTOMATIC_DATA) {
            System.out.println("Enter number of records.");
        }
        else if (choice == MANUAL_DATA) {
            System.out.println("Enter number of records.");
        }
    }
}

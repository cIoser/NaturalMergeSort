import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
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
        Coordinator cord = new Coordinator();
        cord.sort();
        //readFile("merge");
    }

    public static void readFile(String filename) {
        final int DOUBLE_SIZE = 8;
        final int INT_SIZE = 4;
        filename = "/home/egzosted/JavaProjects/NaturalMergeSort/tmp/" + filename;
        Record record = new Record(0.0, 1);
        int endOfData = 0;
        int count = 0;
        try (InputStream is = new FileInputStream(filename)) {
            byte[] bytes = new byte[DOUBLE_SIZE];
            bytes = new byte[DOUBLE_SIZE];
            while (endOfData != -1)
            {
                count++;
                endOfData = is.read(bytes);
                if (endOfData == -1) {
                    break;
                }
                record.setHeight(ByteBuffer.wrap(bytes).getDouble());
                bytes = new byte[INT_SIZE];
                is.read(bytes);
                record.setWeight(ByteBuffer.wrap(bytes).getInt());
                bytes = new byte[DOUBLE_SIZE];
                is.read(bytes);
                record.setBMI(ByteBuffer.wrap(bytes).getDouble());
                System.out.printf("%f\t%d\t%f\t%d\n", record.getHeight(), record.getWeight(), record.getBMI(), count);
            }
        }
        catch (IOException ex) {
            System.out.println("Buffer couldn't have been read");
        }
        System.out.println("---------------------------");
    }
}

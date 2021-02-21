import java.util.Scanner;
import java.util.Random;

public class Generator {
    private int recordsCount;
    private final Random random;

    public Generator() {
        random = new Random();
    }

    public void fromKeyboard() {
        Scanner input = new Scanner(System.in);
        Buffer buffer = new Buffer();
        Record record = new Record(0.0, 0);

        for (int i=1; i<=recordsCount; i++) {
            System.out.printf("Enter height of %d person: ", i);
            record.setHeight(input.nextDouble());

            System.out.printf("Enter weight of %d person: ", i);
            record.setWeight(input.nextInt());

            buffer.insert(record);
        }
        buffer.write();
    }

    public void generateRandom() {
        Buffer buffer = new Buffer();
        Record record = new Record(0.0, 0);

        final double MIN_HEIGHT = 1.50;
        final int MAX_WEIGHT = 150;
        final int MIN_WEIGHT = 50;

        for (int i=1; i<=recordsCount; i++) {
            record.setHeight(random.nextDouble() + MIN_HEIGHT);
            record.setWeight(random.nextInt(MAX_WEIGHT - MIN_WEIGHT) + MIN_WEIGHT);
            buffer.insert(record);
        }

        buffer.write();
        buffer.read();
        buffer.print();
        buffer.read();
        buffer.print();
    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }
}

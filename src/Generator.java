import java.util.Scanner;
import java.util.Random;

public class Generator {
    private int recordsCount;
    private Random random;

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

    }

    public void setRecordsCount(int recordsCount) {
        this.recordsCount = recordsCount;
    }
}

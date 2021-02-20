import java.io.*;
import java.nio.ByteBuffer;

public class Buffer {
    private final int SIZE = 25;
    private int fill;
    private Record[] records;

    public Buffer() {
        fill = 0;
        records = new Record[SIZE];
        for (int i=0;i<SIZE;i++) {
            records[i] = new Record(0.0,0);
        }
    }

    public void insert(Record record) {
        records[fill].setHeight(record.getHeight());
        records[fill].setWeight(record.getWeight());
        records[fill].updateBMI();

        fill++;
        if (fill == SIZE) {
            write();
        }
    }

    public void write() {
        try (OutputStream os = new FileOutputStream("/home/egzosted/JavaProjects/NaturalMergeSort/tmp/merge")) {
            byte[] bytes;
            for (int i=0;i<fill;i++) {
                os.write(ByteBuffer.allocate(8).putDouble(records[i].getHeight()).array());
                os.write(ByteBuffer.allocate(4).putInt(records[i].getWeight()).array());
                os.write(ByteBuffer.allocate(8).putDouble(records[i].getBMI()).array());
            }
        }
        catch (IOException ex) {
            System.out.println("Buffer couldn't have been written");
        }

        fill = 0;
    }

    public void read() {
        fill = 0;
        try (InputStream is = new FileInputStream("/home/egzosted/JavaProjects/NaturalMergeSort/tmp/merge")) {
            byte[] bytes = new byte[8];
            for (int i=0;i<2;i++) {
                bytes = new byte[8];
                is.read(bytes);
                records[i].setHeight(ByteBuffer.wrap(bytes).getDouble());

                bytes = new byte[4];
                is.read(bytes);
                records[i].setWeight(ByteBuffer.wrap(bytes).getInt());

                bytes = new byte[8];
                is.read(bytes);
                records[i].setBMI(ByteBuffer.wrap(bytes).getDouble());

                fill++;
            }
        }
        catch (IOException ex) {
            System.out.println("Buffer couldn't have been read");
        }
    }

    public void print() {
        for (int i=0;i<fill;i++) {
            System.out.printf("%f\t%d\t%f\n", records[i].getHeight(), records[i].getWeight(), records[i].getBMI());
        }
    }
}

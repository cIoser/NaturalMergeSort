import java.io.*;
import java.nio.ByteBuffer;

public class Buffer {
    private static final int DOUBLE_SIZE = 8;
    private static final int INT_SIZE = 4;
    private final int SIZE = 25;
    private final String PATH = "/home/egzosted/JavaProjects/NaturalMergeSort/tmp/";
    public int position;
    private int fill;
    private int nextRecord;
    private Record[] records;
    boolean append;
    private String filename;

    public Buffer(String filename) {
        this.filename = PATH + filename;
        append = false;
        fill = 0;
        nextRecord = 0;
        position = 0;
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
        try (OutputStream os = new FileOutputStream(filename, append)) {
            append = true;
            byte[] bytes;
            for (int i=0;i<fill;i++) {
                os.write(ByteBuffer.allocate(Double.BYTES).putDouble(records[i].getHeight()).array());
                os.write(ByteBuffer.allocate(Integer.BYTES).putInt(records[i].getWeight()).array());
                os.write(ByteBuffer.allocate(Double.BYTES).putDouble(records[i].getBMI()).array());

            }
        }
        catch (IOException ex) {
            System.out.println("Buffer couldn't have been written");
        }
        fill = 0;
    }

    public void read() {
        fill = 0;
        int endOfData = 0;
        try (InputStream is = new FileInputStream(filename)) {
            is.skip(position);
            byte[] bytes = new byte[DOUBLE_SIZE];
            for (int i=0;i<SIZE;i++) {
                bytes = new byte[DOUBLE_SIZE];
                endOfData = is.read(bytes);
                if (endOfData == -1) {
                    break;
                }
                records[i].setHeight(ByteBuffer.wrap(bytes).getDouble());
                position += Double.BYTES;
                bytes = new byte[INT_SIZE];
                is.read(bytes);
                records[i].setWeight(ByteBuffer.wrap(bytes).getInt());
                position += Integer.BYTES;
                bytes = new byte[DOUBLE_SIZE];
                is.read(bytes);
                records[i].setBMI(ByteBuffer.wrap(bytes).getDouble());
                position += Double.BYTES;
                fill++;

            }
        }
        catch (IOException ex) {
            System.out.println("Buffer couldn't have been read");
        }
    }

    public Record getNext() {
        return records[nextRecord];
    }

    public void print() {
        read();
        for (int i=0;i<fill;i++) {
            System.out.printf("%d\t%f\t%d\t%f\n", i, records[i].getHeight(), records[i].getWeight(), records[i].getBMI());

            if (i + 1 == fill) {
                read();
                i = 0;
                if (fill == 0) {
                    break;
                }
            }
        }
    }
}

public class Tape {
    private final Buffer buff;

    public Tape(String filename) {
        buff = new Buffer(filename);
    }

    public boolean insertRun(Tape source) {
        Record mock = new Record(100.0, 1);
        Record next;
        while (true) {
            next = source.buff.getNext();
            if (next.getHeight() == -1.0) {
                return true;
            }
            if (next.getBMI() < mock.getBMI()) {
                source.buff.setNextRecord(source.buff.getNextRecord() - 1);
                return false;
            }
            buff.insert(next);
            mock = next;
        }
    }

    public Buffer getBuffer() {
        return buff;
    }
}


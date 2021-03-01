public class Tape {
    private final Buffer buff;

    public Tape(String filename) {
        buff = new Buffer(filename);
    }

    public boolean insertRun(Tape source) {
        Record mock = new Record(1.0, 0);
        Record next;
        while (true) {
            next = source.buff.getNext();
            if (next.getHeight() == 1.0) {
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

    public void endTape(Tape source, Record next) {
        while(next.getHeight() != 1.0) {
            buff.insert(next);
            next = source.buff.getNext();
        }
    }

    public void endRun(Tape source, Record next, Record mock) {
        while(next.getBMI() > mock.getBMI()) {
            buff.insert(next);
            mock = next;
            next = source.buff.getNext();
        }
    }

    public boolean mergeTapes(Tape distributor1, Tape distributor2) {
        Record next1 = distributor1.buff.getNext();
        Record next2 = distributor2.buff.getNext();
        Record mock1 = new Record(1.0, 0);
        Record mock2 = new Record(1.0, 0);

        boolean end = false;
        int i = 0;
        while(!end) {
            if (next1.getHeight() == 1.0) {
                endTape(distributor2, next2);
                end = true;
                if (i == 0) {
                    buff.write();
                    return true;
                }
            }
            else if (next2.getHeight() == 1.0) {
                endTape(distributor1, next1);
                end = true;
                if (i == 0) {
                    buff.write();
                    return true;
                }
            }
            else if (next1.getBMI() < mock1.getBMI()) {
                endRun(distributor2, next2, mock2);
            }
            else if (next2.getBMI() < mock2.getBMI()) {
                endRun(distributor1, next1, mock1);
            }
            else {
                if (next1.getBMI() < next2.getBMI()) {
                    buff.insert(next1);
                    mock1 = next1;
                    next1 = distributor1.buff.getNext();
                }
                else {
                    buff.insert(next2);
                    mock2 = next2;
                    next2 = distributor2.buff.getNext();
                }
            }
            i++;
        }
        buff.write();
        return false;
    }

    public Buffer getBuffer() {
        return buff;
    }
}


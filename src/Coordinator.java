public class Coordinator {
    public final int DISTRIBUTORS_COUNT = 2;
    public static final String DISTRIBUTOR1_FILENAME = "distributor1";
    public static final String DISTRIBUTOR2_FILENAME = "distributor2";
    public static final String MERGE_FILENAME = "merge";
    public Coordinator() {

    }

    public void sort() {
        boolean sorted = false;
        while(!sorted) {
            distribute();
            sorted = merge();
        }
    }

    private void distribute() {
        Tape[] distributors = new Tape[DISTRIBUTORS_COUNT];
        distributors[0] = new Tape(DISTRIBUTOR1_FILENAME);
        distributors[1] = new Tape(DISTRIBUTOR2_FILENAME);
        Tape merger = new Tape(MERGE_FILENAME);
        int currentDistributor = 0;
        boolean endOfDistribution = false;

        while(!endOfDistribution) {
            endOfDistribution = distributors[currentDistributor].insertRun(merger);
            currentDistributor = (++currentDistributor) % DISTRIBUTORS_COUNT;
        }

        distributors[0].getBuffer().write();
        distributors[1].getBuffer().write();
    }

    private boolean merge() {
        Tape[] distributors = new Tape[DISTRIBUTORS_COUNT];
        distributors[0] = new Tape(DISTRIBUTOR1_FILENAME);
        distributors[1] = new Tape(DISTRIBUTOR2_FILENAME);
        Tape merger = new Tape(MERGE_FILENAME);
        return merger.mergeTapes(distributors[0], distributors[1]);
    }
}

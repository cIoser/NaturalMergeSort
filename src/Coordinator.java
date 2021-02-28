public class Coordinator {
    private int phaseCount;

    public Coordinator() {
        phaseCount = 0;
    }

    public void sort() {
        boolean sorted = false;
        while(!sorted) {
            distribute();
            merge();
            sorted = true;
        }
    }

    private void distribute() {
        Tape[] distributors = new Tape[2];
        distributors[0] = new Tape("distributor1");
        distributors[1] = new Tape("distributor2");
        Tape merger = new Tape("merge");
        int currentDistributor = 0;
        boolean endOfDistribution = false;

        while(!endOfDistribution) {
            endOfDistribution = distributors[currentDistributor].insertRun(merger);
            currentDistributor = (currentDistributor + 1) % 2;
        }
        distributors[0].getBuffer().write();
        distributors[1].getBuffer().write();
    }

    private void merge() {

    }

    public int getPhaseCount(){
        return phaseCount;
    }

    public void setPhaseCount(int phaseCount) {
        this.phaseCount = phaseCount;
    }
}

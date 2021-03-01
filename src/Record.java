public class Record {
    private double height; // height in metres
    private int weight; // weight in kilos
    private double BMI;

    public Record(double height, int weight) {
        this.height = height;
        this.weight = weight;
        updateBMI();
    }

    public double getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public double getBMI() {
        return BMI;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBMI(double BMI) {
        this.BMI = BMI;
    }

    public void updateBMI() {
        this.BMI = weight / Math.pow(height, 2);
    }
}

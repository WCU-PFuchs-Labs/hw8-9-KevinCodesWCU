public class DataRow {
    private final double y;
    private final double[] x;

    public DataRow(double y, double[] x) {
        this.y = y;
        this.x = x;
    }

    public double getY() { return y; }
    public double[] getX() { return x; }
}

public class Divide extends Binop {
    @Override
    public double apply(double a, double b) {
        if (Math.abs(b) < 0.0001) return 1.0;
        return a / b;
    }

    @Override
    public String symbol() { return "/"; }
}

public class Minus extends Binop {
    @Override public double apply(double a, double b) { return a - b; }
    @Override public String symbol() { return "-"; }
}

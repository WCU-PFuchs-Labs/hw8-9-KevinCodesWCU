public abstract class Binop extends Op {
    @Override
    public abstract double apply(double a, double b);

    @Override
    public abstract String symbol();
}

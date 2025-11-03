public abstract class Op implements Cloneable {

    public abstract double apply(double a, double b);

    public abstract String symbol();

    @Override
    public Object clone() {
        Object copy = null;
        try {
            copy = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Op can't clone");
        }
        return copy;
    }
}

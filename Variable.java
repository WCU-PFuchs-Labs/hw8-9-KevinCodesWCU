
public class Variable extends Unop {

    private final int idx;

    public Variable(int idx) {
        this.idx = idx;
    }

    @Override
    public double eval(double[] inputs) {
        if (idx < 0 || idx >= inputs.length) {
            throw new IllegalArgumentException("Missing input for variable X" + idx);
        }
        return inputs[idx];
    }

    @Override
    public String toString() {
        return "X" + idx;
    }

}

import java.util.Random;

public class Node implements Cloneable {

    private Unop terminal;
    private Op operation;
    private Node left, right;
    protected int depth;

    public Node(Unop terminal) {
        this.terminal = terminal;
        this.operation = null;
        this.left = null;
        this.right = null;
        this.depth = 0;
    }

    public Node(Op op, Node left, Node right) {
        this.terminal = null;
        this.operation = op;
        this.left = left;
        this.right = right;
        this.depth = 0;
    }

    public double eval(double[] values) {
        if (terminal != null) {
            return terminal.eval(values);
        }
        double a = left.eval(values);
        double b = right.eval(values);
        return operation.apply(a, b);
    }

    @Override
    public String toString() {
        if (terminal != null) return terminal.toString();
        return "(" + left + " " + operation.symbol() + " " + right + ")";
    }

    public void addRandomKids(NodeFactory factory, int maxDepth, Random rng) {
        if (terminal != null) return;

        if (this.depth >= maxDepth) {
            this.left = factory.getTerminal(rng);
            this.right = factory.getTerminal(rng);
            this.left.depth = this.depth + 1;
            this.right.depth = this.depth + 1;
            return;
        }

        int total = factory.getNumOps() + factory.getNumIndepVars();
        int rollLeft = rng.nextInt(total + 1);

        if (rollLeft < factory.getNumOps()) {
            this.left = factory.getOperator(rng);
        } else {
            this.left = factory.getTerminal(rng);
        }
        this.left.depth = this.depth + 1;

        if (this.left.terminal == null) {
            this.left.addRandomKids(factory, maxDepth, rng);
        }

        int rollRight = rng.nextInt(total + 1);
        if (rollRight < factory.getNumOps()) {
            this.right = factory.getOperator(rng);
        } else {
            this.right = factory.getTerminal(rng);
        }
        this.right.depth = this.depth + 1;

        if (this.right.terminal == null) {
            this.right.addRandomKids(factory, maxDepth, rng);
        }
    }

    @Override
    public Object clone() {
        Object cloneObj = null;
        try {
            cloneObj = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Node can't clone");
            return null;
        }

        Node copy = (Node) cloneObj;
        if (this.left != null)  copy.left = (Node) this.left.clone();
        if (this.right != null) copy.right = (Node) this.right.clone();
        if (this.operation != null) copy.operation = (Op) this.operation.clone();

        return copy;
    }

    public void traverse(Collector c) {
        c.collect(this);
        if (left != null)  left.traverse(c);
        if (right != null) right.traverse(c);
    }

    public void swapLeft(Node trunk) {
        Node tmp = this.left;
        this.left = trunk.left;
        trunk.left = tmp;
    }

    public void swapRight(Node trunk) {
        Node tmp = this.right;
        this.right = trunk.right;
        trunk.right = tmp;
    }

    public boolean isLeaf() {
        return this.terminal != null;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


}

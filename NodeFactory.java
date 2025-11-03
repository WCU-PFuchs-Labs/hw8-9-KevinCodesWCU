import java.util.Random;

public class NodeFactory {
    private final int numIndepVars;
    private final Binop[] currentOps;
    private final Node[] operatorPrototypes;

    public NodeFactory(Binop[] ops, int numVars) {
        this.currentOps = ops;
        this.numIndepVars = numVars;
        this.operatorPrototypes = new Node[ops.length];
        for (int i = 0; i < ops.length; i++) {
            this.operatorPrototypes[i] = new Node(ops[i], null, null);
        }
    }

    public Node getOperator(Random rand) {
        return (Node) operatorPrototypes[rand.nextInt(operatorPrototypes.length)].clone();
    }

    public int getNumOps() {
        return currentOps.length;
    }

    public Node getTerminal(Random rand) {
        if (rand.nextInt(numIndepVars + 1) < numIndepVars) {
            return new Node(new Variable(rand.nextInt(numIndepVars)));
        } else {
            return new Node(new Const(rand.nextDouble()));
        }
    }

    public int getNumIndepVars() {
        return numIndepVars;
    }
}

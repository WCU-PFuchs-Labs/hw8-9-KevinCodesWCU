import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Generation {
    private final Random rand = new Random();
    private final DataSet dataSet;
    private final NodeFactory factory;
    private final int maxDepth;
    private GPTree[] pop;

    public Generation(int size, int maxDepth, String fileName) throws Exception {
        this.maxDepth = maxDepth;
        this.dataSet = new DataSet(fileName);

        int numIndepVars = this.dataSet.getRow(0).getX().length;
        Binop[] ops = { new Plus(), new Minus(), new Mult(), new Divide() };
        this.factory = new NodeFactory(ops, numIndepVars);

        this.pop = new GPTree[size];
        for (int i = 0; i < size; i++) {
            pop[i] = new GPTree(factory, maxDepth, rand);
        }
    }

    public void evalAll() {
        for (GPTree t : pop) t.evalFitness(dataSet);
        Arrays.sort(pop);
    }

    public ArrayList<GPTree> getTopTen() {
        int k = Math.min(10, pop.length);
        ArrayList<GPTree> top = new ArrayList<>(k);
        for (int i = 0; i < k; i++) top.add(pop[i]);
        return top;
    }

    public void printBestFitness() {
        if (pop.length == 0) return;
        System.out.println(pop[0].getFitness());
    }

    public void printBestTree() {
        if (pop.length == 0) return;
        System.out.println(pop[0]);
    }

    public void evolve() {
        GPTree[] next = new GPTree[pop.length];
        int elitePool = Math.max(2, pop.length / 2);

        for (int i = 0; i < pop.length; i += 2) {
            GPTree p1 = pop[rand.nextInt(elitePool)];
            GPTree p2 = pop[rand.nextInt(elitePool)];
            GPTree c1 = (GPTree) p1.clone();
            GPTree c2 = (GPTree) p2.clone();
            c1.crossover(c2, rand);
            next[i] = c1;
            if (i + 1 < pop.length) next[i + 1] = c2;
        }
        pop = next;
    }
}

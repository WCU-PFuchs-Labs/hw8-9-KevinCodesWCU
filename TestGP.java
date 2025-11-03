import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestGP {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter data file path: ");
        String file = br.readLine().trim();

        int size = 500;
        int maxDepth = 3;

        Generation gen = new Generation(size, maxDepth, file);

        gen.evalAll();
        System.out.println("Generation 1:");
        gen.printBestTree();
        gen.printBestFitness();

        for (int g = 2; g <= 50; g++) {
            gen.evolve();
            gen.evalAll();
            System.out.println("Generation " + g + ":");
            gen.printBestTree();
            gen.printBestFitness();
        }
    }
}

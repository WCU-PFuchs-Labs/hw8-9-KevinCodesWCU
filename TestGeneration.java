import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TestGeneration {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter data file path: ");
        String file = br.readLine().trim();

        int size = 500;
        int maxDepth = 3;

        Generation gen = new Generation(size, maxDepth, file);
        gen.evalAll();

        System.out.println("Best Tree:");
        gen.printBestTree();
        System.out.println("Best Fitness:");
        gen.printBestFitness();

        ArrayList<GPTree> top = gen.getTopTen();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.print("Top Ten Fitness Values:");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < top.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(df.format(top.get(i).getFitness()));
        }
        System.out.println(sb.toString());
    }
}


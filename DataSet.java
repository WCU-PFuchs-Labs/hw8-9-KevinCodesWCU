import java.io.*;
import java.util.*;

public class DataSet {
    private final ArrayList<DataRow> rows = new ArrayList<>();

    public DataSet(String fileName) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            if (line == null) return;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                double y = Double.parseDouble(parts[0].trim());
                double[] x = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    x[i - 1] = Double.parseDouble(parts[i].trim());
                }
                rows.add(new DataRow(y, x));
            }
        }
    }

    public int size() { return rows.size(); }

    public DataRow getRow(int i) { return rows.get(i); }
}

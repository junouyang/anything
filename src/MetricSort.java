import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jun.ouyang on 10/13/17.
 */
public class MetricSort {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("metrics.txt"));
        String line = null;

        Map<String, Set<String>> component2Apps = new HashMap<>();
        while((line = bufferedReader.readLine()) != null) {
            String[] split = line.trim().split(",\\s+");

            String metricId = split[0];
            String appId = split[1];
            String metricName = split[2];

            String[] metricSplit = metricName.split("[:|]");
            component2Apps.computeIfAbsent(metricSplit[3], key -> new HashSet<>()).add(metricSplit[8]);
        }

        for(Map.Entry<String, Set<String>> entry : component2Apps.entrySet()) {
            if( entry.getValue().size() > 1) {
                System.out.println( entry.getKey() + ", " + entry.getValue());
            }
        }

        bufferedReader.close();
    }
}

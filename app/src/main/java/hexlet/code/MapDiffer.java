package hexlet.code;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapDiffer {

    public static List<Node> getDiff(Map<String, Object> config1, Map<String, Object> config2) {
        List<Node> nodeList = new LinkedList<>();
        Set<String> keys = new LinkedHashSet<>(config1.keySet());

        keys.addAll(config2.keySet());
        SortedSet<String> sortedKeys = new TreeSet<>(keys);

        for (String key: sortedKeys) {
            Object value1 = config1.get(key);
            Object value2 = config2.get(key);

            if (config1.containsKey(key) && config2.containsKey(key)) {
                if (value1 == null || value2 == null) {
                    if (value1 == value2) {
                        nodeList.add(new Node(key, config1.get(key), "unchanged"));
                    } else {
                        nodeList.add(new Node(key, config2.get(key), "changed", config1.get(key)));
                    }
                } else {
                    if (value1.equals(value2)) {
                        nodeList.add(new Node(key, config1.get(key), "unchanged"));
                    } else {
                        nodeList.add(new Node(key, config2.get(key), "changed", config1.get(key)));
                    }
                }
            } else if (!config1.containsKey(key) && config2.containsKey(key)) {
                nodeList.add(new Node(key, config2.get(key), "added"));
            } else if (config1.containsKey(key) && !config2.containsKey(key)) {
                nodeList.add(new Node(key, config1.get(key), "deleted"));
            }
        }

        return nodeList;
    }
}

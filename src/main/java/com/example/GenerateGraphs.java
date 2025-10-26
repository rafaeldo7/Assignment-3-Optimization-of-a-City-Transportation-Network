package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GenerateGraphs {

    private static Random random = new Random();

    public static void main(String[] args) throws IOException {
        JSONArray graphs = new JSONArray();

        generateGraphs(graphs, "small", 5, 4, 6, 6, 15);
        generateGraphs(graphs, "medium", 10, 10, 15, 20, 80);
        generateGraphs(graphs, "large", 10, 20, 30, 60, 200);
        generateGraphs(graphs, "extra", 5, 50, 300, 300, 1500);

        JSONObject root = new JSONObject();
        root.put("graphs", graphs);

        try (FileWriter file = new FileWriter("src/main/resources/graph_input.json")) {
            file.write(root.toString(2));
        }

        System.out.println("✅ Graph input file generated successfully at src/main/resources/graph_input.json");
    }

    private static void generateGraphs(JSONArray graphs, String category, int count,
                                       int minV, int maxV, int minE, int maxE) {
        for (int i = 0; i < count; i++) {
            int numVertices = random.nextInt(maxV - minV + 1) + minV;
            int numEdges = random.nextInt(maxE - minE + 1) + minE;

            List<String> nodes = new ArrayList<>();
            for (int v = 0; v < numVertices; v++) {
                nodes.add("N" + (v + 1));
            }

            Set<String> usedEdges = new HashSet<>();
            JSONArray edges = new JSONArray();

            while (edges.length() < numEdges) {
                String from = nodes.get(random.nextInt(nodes.size()));
                String to = nodes.get(random.nextInt(nodes.size()));
                if (from.equals(to)) continue;

                String edgeKey = from + "-" + to;
                String edgeKeyReverse = to + "-" + from;
                if (usedEdges.contains(edgeKey) || usedEdges.contains(edgeKeyReverse)) continue;

                usedEdges.add(edgeKey);
                int weight = random.nextInt(90) + 10;

                JSONObject edge = new JSONObject();
                edge.put("from", from);
                edge.put("to", to);
                edge.put("weight", weight);
                edges.put(edge);
            }

            JSONObject graph = new JSONObject();
            graph.put("id", graphs.length() + 1);
            graph.put("category", category);
            graph.put("nodes", new JSONArray(nodes));
            graph.put("edges", edges);

            graphs.put(graph);
        }
    }
}

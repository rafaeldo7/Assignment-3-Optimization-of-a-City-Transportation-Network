package com.example;

import com.example.graph.*;
import com.example.algorithms.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String inputFile = "src/main/resources/graph_input.json";
        String outputFile = "src/main/resources/output.json";

        try (FileInputStream fis = new FileInputStream(inputFile)) {
            JSONArray graphs = new JSONArray(new JSONTokener(fis));
            JSONArray resultsArray = new JSONArray();

            int graphId = 1;
            for (Object obj : graphs) {
                JSONObject gObj = (JSONObject) obj;
                Graph graph = new Graph();

                JSONArray edges = gObj.getJSONArray("edges");
                for (Object eObj : edges) {
                    JSONObject e = (JSONObject) eObj;
                    graph.addEdge(e.getString("from"), e.getString("to"), e.getInt("weight"));
                }

                KruskalAlgorithm kruskal = new KruskalAlgorithm();
                KruskalAlgorithm.Result kResult = kruskal.findMST(graph);

                PrimAlgorithm prim = new PrimAlgorithm();
                PrimAlgorithm.Result pResult = prim.findMST(graph);

                JSONObject graphResult = new JSONObject();
                graphResult.put("graph_id", graphId++);
                JSONObject inputStats = new JSONObject();
                inputStats.put("vertices", graph.getVertexCount());
                inputStats.put("edges", graph.getEdgeCount() / 2); // неориентированные
                graphResult.put("input_stats", inputStats);

                graphResult.put("prim", toJsonResult(pResult));
                graphResult.put("kruskal", toJsonResult(kResult));

                resultsArray.put(graphResult);
            }

            JSONObject finalOutput = new JSONObject();
            finalOutput.put("results", resultsArray);

            try (FileWriter fw = new FileWriter(outputFile)) {
                fw.write(finalOutput.toString(4));
            }

            System.out.println("✅ Results saved to " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject toJsonResult(Object res) {
        JSONObject obj = new JSONObject();

        if (res instanceof KruskalAlgorithm.Result kr) {
            obj.put("mst_edges", toJsonEdges(kr.mstEdges));
            obj.put("total_cost", kr.totalCost);
            obj.put("operations_count", kr.operationsCount);
            obj.put("execution_time_ms", kr.executionTimeMs);
        } else if (res instanceof PrimAlgorithm.Result pr) {
            obj.put("mst_edges", toJsonEdges(pr.mstEdges));
            obj.put("total_cost", pr.totalCost);
            obj.put("operations_count", pr.operationsCount);
            obj.put("execution_time_ms", pr.executionTimeMs);
        }

        return obj;
    }

    private static JSONArray toJsonEdges(List<Edge> edges) {
        JSONArray arr = new JSONArray();
        for (Edge e : edges) {
            JSONObject obj = new JSONObject();
            obj.put("from", e.getFrom());
            obj.put("to", e.getTo());
            obj.put("weight", e.getWeight());
            arr.put(obj);
        }
        return arr;
    }
}

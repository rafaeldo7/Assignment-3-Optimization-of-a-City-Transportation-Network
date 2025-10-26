package com.example.algorithms;

import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.UnionFind;

import java.util.*;

public class KruskalAlgorithm {

    public static class Result {
        public List<Edge> mstEdges;
        public int totalCost;
        public int operationsCount;
        public double executionTimeMs;

        public Result(List<Edge> mstEdges, int totalCost, int operationsCount, double executionTimeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTimeMs = executionTimeMs;
        }
    }

    public Result findMST(Graph graph) {
        long startTime = System.nanoTime();

        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        Collections.sort(sortedEdges);

        UnionFind uf = new UnionFind();
        for (String vertex : graph.getVertices()) {
            uf.makeSet(vertex);
        }

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        for (Edge edge : sortedEdges) {
            String from = edge.getFrom();
            String to = edge.getTo();

            if (uf.union(from, to)) {
                mst.add(edge);
                totalCost += edge.getWeight();
            }
        }

        long endTime = System.nanoTime();
        double execTimeMs = (endTime - startTime) / 1_000_000.0;

        return new Result(
                mst,
                totalCost,
                uf.getOperationsCount(),
                execTimeMs
        );
    }
}

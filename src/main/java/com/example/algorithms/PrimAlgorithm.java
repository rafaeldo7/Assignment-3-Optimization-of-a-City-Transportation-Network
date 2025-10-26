package com.example.algorithms;

import com.example.graph.Edge;
import com.example.graph.Graph;

import java.util.*;

public class PrimAlgorithm {

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

        Set<String> visited = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        String start = graph.getVertices().iterator().next();
        visited.add(start);
        pq.addAll(graph.getAdjacencyList().get(start));

        int totalCost = 0;
        int operations = 0;

        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            Edge edge = pq.poll();
            operations++;

            if (visited.contains(edge.getTo())) continue;

            visited.add(edge.getTo());
            mstEdges.add(edge);
            totalCost += edge.getWeight();

            for (Edge next : graph.getAdjacencyList().get(edge.getTo())) {
                if (!visited.contains(next.getTo())) {
                    pq.add(next);
                }
            }
        }

        long endTime = System.nanoTime();
        double execTimeMs = (endTime - startTime) / 1_000_000.0;

        return new Result(mstEdges, totalCost, operations, execTimeMs);
    }
}

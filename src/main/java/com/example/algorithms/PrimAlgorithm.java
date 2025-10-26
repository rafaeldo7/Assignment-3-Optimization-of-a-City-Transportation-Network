package com.example.algorithms;

import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.Result;

import java.util.*;

public class PrimAlgorithm {
    private Graph graph;

    public PrimAlgorithm() {
        // Пустой конструктор
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Result computeMST() {
        Result result = new Result();

        long startTime = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        int operations = 0;

        if (graph.getVertices().isEmpty()) return result;

        String start = graph.getVertices().get(0);
        visited.add(start);

        // добавляем все рёбра, исходящие из стартовой вершины
        for (Edge e : graph.getEdges()) {
            if (e.getFrom().equals(start) || e.getTo().equals(start)) {
                pq.offer(e);
                operations++;
            }
        }

        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            Edge e = pq.poll();
            operations++;

            String next = null;
            if (visited.contains(e.getFrom()) && !visited.contains(e.getTo())) {
                next = e.getTo();
            } else if (visited.contains(e.getTo()) && !visited.contains(e.getFrom())) {
                next = e.getFrom();
            } else {
                continue;
            }

            mstEdges.add(e);
            visited.add(next);

            // добавляем новые рёбра
            for (Edge edge : graph.getEdges()) {
                if ((edge.getFrom().equals(next) && !visited.contains(edge.getTo())) ||
                    (edge.getTo().equals(next) && !visited.contains(edge.getFrom()))) {
                    pq.offer(edge);
                    operations++;
                }
            }
        }

        long endTime = System.nanoTime();
        double elapsedMs = (endTime - startTime) / 1_000_000.0;

        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();

        result.setMstEdges(mstEdges);
        result.setTotalCost(totalCost);
        result.setOperationsCount(operations);
        result.setExecutionTimeMs(elapsedMs);

        return result;
    }
}


package com.example.algorithms;

import com.example.graph.Edge;
import com.example.graph.Graph;
import com.example.graph.Result;
import com.example.graph.UnionFind;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {
    private Graph graph;

    public KruskalAlgorithm() {
        // пустой конструктор
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Result computeMST() {
        Result result = new Result();

        long startTime = System.nanoTime();

        List<Edge> mstEdges = new ArrayList<>();
        int operations = 0;

        // сортируем рёбра по весу
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight));
        operations += edges.size(); // учитываем сортировку

        // инициализация Union-Find
        UnionFind uf = new UnionFind(graph.getVertices());
        operations += graph.getVertices().size();

        for (Edge e : edges) {
            operations++;
            if (uf.find(e.getFrom()) != uf.find(e.getTo())) {
                uf.union(e.getFrom(), e.getTo());
                mstEdges.add(e);
                operations++;
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


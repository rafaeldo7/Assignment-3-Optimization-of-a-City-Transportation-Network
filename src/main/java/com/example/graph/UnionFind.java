package com.example.graph;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();
    private int operationsCount = 0;

    public void makeSet(String vertex) {
        parent.put(vertex, vertex);
        rank.put(vertex, 0);
        operationsCount++;
    }

    public String find(String vertex) {
        operationsCount++;
        if (!parent.get(vertex).equals(vertex)) {
            parent.put(vertex, find(parent.get(vertex))); // path compression
        }
        return parent.get(vertex);
    }

    public boolean union(String root1, String root2) {
        String parent1 = find(root1);
        String parent2 = find(root2);

        if (parent1.equals(parent2)) {
            return false;
        }

        // Union by rank
        if (rank.get(parent1) < rank.get(parent2)) {
            parent.put(parent1, parent2);
        } else if (rank.get(parent1) > rank.get(parent2)) {
            parent.put(parent2, parent1);
        } else {
            parent.put(parent2, parent1);
            rank.put(parent1, rank.get(parent1) + 1);
        }

        operationsCount++;
        return true;
    }

    public int getOperationsCount() {
        return operationsCount;
    }
}

package com.example.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
    private Map<String, String> parent;

    public UnionFind(List<String> vertices) {
        parent = new HashMap<>();
        for (String v : vertices) {
            parent.put(v, v);
        }
    }

    public String find(String v) {
        if (!parent.get(v).equals(v)) {
            parent.put(v, find(parent.get(v))); // path compression
        }
        return parent.get(v);
    }

    public void union(String a, String b) {
        String rootA = find(a);
        String rootB = find(b);
        if (!rootA.equals(rootB)) {
            parent.put(rootA, rootB);
        }
    }
}


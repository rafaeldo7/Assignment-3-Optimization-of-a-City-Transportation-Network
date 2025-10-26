package com.example.graph;

import java.util.*;

public class Graph {
    private final Set<String> vertices;
    private final List<Edge> edges;
    private final Map<String, List<Edge>> adjacencyList;

    public Graph() {
        vertices = new HashSet<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        vertices.add(vertex);
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(String from, String to, int weight) {
        addVertex(from);
        addVertex(to);
        Edge edge = new Edge(from, to, weight);
        edges.add(edge);
        adjacencyList.get(from).add(edge);
        adjacencyList.get(to).add(new Edge(to, from, weight)); // для неориентированного графа
    }

    public Set<String> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return adjacencyList;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }
}

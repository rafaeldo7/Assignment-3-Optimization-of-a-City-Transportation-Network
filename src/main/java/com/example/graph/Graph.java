package com.example.graph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<String> vertices;
    private List<Edge> edges;

    public Graph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public List<String> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addVertex(String v) {
        if (!vertices.contains(v)) vertices.add(v);
    }

    public void addEdge(String from, String to, int weight) {
        Edge e = new Edge(from, to, weight);
        edges.add(e);
        addVertex(from);
        addVertex(to);
    }

    // Создание графа из JSON с ключом "nodes"
    public static Graph createFromJson(JSONObject json) {
        Graph g = new Graph();

        // читаем вершины
        if (json.has("nodes")) {
            JSONArray verts = json.getJSONArray("nodes");
            for (int i = 0; i < verts.length(); i++) {
                g.addVertex(verts.getString(i));
            }
        }

        // читаем рёбра
        if (json.has("edges")) {
            JSONArray eds = json.getJSONArray("edges");
            for (int i = 0; i < eds.length(); i++) {
                JSONObject e = eds.getJSONObject(i);
                String from = e.getString("from");
                String to = e.getString("to");
                int weight = e.getInt("weight");
                g.addEdge(from, to, weight);
            }
        }

        return g;
    }
}


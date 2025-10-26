package com.example;

import com.example.algorithms.KruskalAlgorithm;
import com.example.algorithms.PrimAlgorithm;
import com.example.graph.Edge;
import com.example.graph.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MSTTests {

    private Graph buildSampleGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 4);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 2);
        graph.addEdge("B", "D", 5);
        graph.addEdge("C", "D", 7);
        graph.addEdge("C", "E", 8);
        graph.addEdge("D", "E", 6);
        return graph;
    }

    @Test
    public void testMSTCostEquality() {
        Graph graph = buildSampleGraph();

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        KruskalAlgorithm.Result kr = kruskal.findMST(graph);

        PrimAlgorithm prim = new PrimAlgorithm();
        PrimAlgorithm.Result pr = prim.findMST(graph);

        Assertions.assertEquals(kr.totalCost, pr.totalCost,
                "MST total cost should be the same for both algorithms");
    }

    @Test
    public void testEdgeCountInMST() {
        Graph graph = buildSampleGraph();

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        KruskalAlgorithm.Result kr = kruskal.findMST(graph);

        int expectedEdges = graph.getVertexCount() - 1;
        Assertions.assertEquals(expectedEdges, kr.mstEdges.size(),
                "MST should have exactly V-1 edges");
    }

    @Test
    public void testPerformanceMetrics() {
        Graph graph = buildSampleGraph();

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        KruskalAlgorithm.Result kr = kruskal.findMST(graph);

        Assertions.assertTrue(kr.executionTimeMs >= 0, "Execution time should be non-negative");
        Assertions.assertTrue(kr.operationsCount >= 0, "Operation count should be non-negative");
    }
}

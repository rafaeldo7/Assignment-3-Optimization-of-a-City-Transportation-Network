package com.example;

import com.example.algorithms.KruskalAlgorithm;
import com.example.algorithms.PrimAlgorithm;
import com.example.graph.Graph;
import com.example.graph.Result; 
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
        kruskal.setGraph(graph); // <-- ИСПРАВЛЕНО: Сначала устанавливаем граф
        Result kr = kruskal.computeMST(); // <-- ИСПРАВЛЕНО: Вызываем computeMST()

        PrimAlgorithm prim = new PrimAlgorithm();
        prim.setGraph(graph); // <-- ИСПРАВЛЕНО
        Result pr = prim.computeMST(); // <-- ИСПРАВЛЕНО

        // ИСПРАВЛЕНО: используем геттеры (getTotalCost)
        Assertions.assertEquals(kr.getTotalCost(), pr.getTotalCost(),
                "MST total cost should be the same for both algorithms");
    }

    @Test
    public void testEdgeCountInMST() {
        Graph graph = buildSampleGraph();

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        kruskal.setGraph(graph); // <-- ИСПРАВЛЕНО
        Result kr = kruskal.computeMST(); // <-- ИСПРАВЛЕНО

        // ИСПРАВЛЕНО: используем getVertices().size()
        int expectedEdges = graph.getVertices().size() - 1; 
        
        // ИСПРАВЛЕНО: используем геттер getMstEdges()
        Assertions.assertEquals(expectedEdges, kr.getMstEdges().size(),
                "MST should have exactly V-1 edges");
    }

    @Test
    public void testPerformanceMetrics() {
        Graph graph = buildSampleGraph();

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        kruskal.setGraph(graph); // <-- ИСПРАВЛЕНО
        Result kr = kruskal.computeMST(); // <-- ИСПРАВЛЕНО

        // ИСПРАВЛЕНО: используем геттеры
        Assertions.assertTrue(kr.getExecutionTimeMs() >= 0, "Execution time should be non-negative");
        Assertions.assertTrue(kr.getOperationsCount() >= 0, "Operation count should be non-negative");
    }
}

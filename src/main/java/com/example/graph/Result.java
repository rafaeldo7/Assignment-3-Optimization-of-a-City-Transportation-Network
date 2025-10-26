package com.example.graph;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationsCount;
    private Double executionTimeMs; // можно null, если не замерялось

    public Result() {
        this.mstEdges = new ArrayList<>();
        this.totalCost = 0;
        this.operationsCount = 0;
        this.executionTimeMs = null;
    }

    public List<Edge> getMstEdges() {
        return mstEdges;
    }

    public void setMstEdges(List<Edge> mstEdges) {
        this.mstEdges = mstEdges;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getOperationsCount() {
        return operationsCount;
    }

    public void setOperationsCount(int operationsCount) {
        this.operationsCount = operationsCount;
    }

    public Double getExecutionTimeMs() {
        return executionTimeMs;
    }

    public void setExecutionTimeMs(Double executionTimeMs) {
        this.executionTimeMs = executionTimeMs;
    }
}


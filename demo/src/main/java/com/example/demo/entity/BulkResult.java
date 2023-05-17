package com.example.demo.entity;

import java.util.List;

public class BulkResult {

    private Double MaxSum;
    private Double MinSum;
    private List<Result> results;

    public BulkResult(Double maxSum, Double minSum, List<Result> results) {
        MaxSum = maxSum;
        MinSum = minSum;
        this.results = results;
    }

    public BulkResult(){}

    public Double getMaxSum() {
        return MaxSum;
    }

    public void setMaxSum(Double maxSum) {
        MaxSum = maxSum;
    }

    public Double getMinSum() {
        return MinSum;
    }

    public void setMinSum(Double minSum) {
        MinSum = minSum;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}

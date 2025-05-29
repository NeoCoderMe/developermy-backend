package com.developermy.rag.models;

import java.util.List;

public record QueryResponse(
    String query,
    List<QueryResult> results,
    boolean success,
    String error
) {
    public static QueryResponse success(String query, List<QueryResult> results) {
        return new QueryResponse(query, results, true, null);
    }
    
    public static QueryResponse error(String errorMessage) {
        return new QueryResponse(null, null, false, errorMessage);
    }
}
package com.developermy.rag.models;

public record LoadResponse(
	    int recordsProcessed,
	    int recordsSuccess,
	    boolean success,
	    String error
	) {
	    public static LoadResponse success(int processed, int successful) {
	        return new LoadResponse(processed, successful, true, null);
	    }
	    
	    public static LoadResponse error(String errorMessage) {
	        return new LoadResponse(0, 0, false, errorMessage);
	    }
	}

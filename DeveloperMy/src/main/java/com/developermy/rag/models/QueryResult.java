package com.developermy.rag.models;

public record QueryResult(
	    String question,
	    String answer,
	    double similarity
	) {
	}
package com.developermy.rag.models;


public record VectorEntry(String question, String answer, float[] vector) {
}

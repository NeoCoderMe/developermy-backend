package com.developermy.feature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FeatureResponseDTO(@JsonProperty("full_name") String fullName) {
	
}

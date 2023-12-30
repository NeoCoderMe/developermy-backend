package com.developermy.feature.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FeatureRequestDTO(@JsonProperty("full_name") String fullName, String password) {

}

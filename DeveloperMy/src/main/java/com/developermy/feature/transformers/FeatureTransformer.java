package com.developermy.feature.transformers;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;

/*
 Hide Utility Class Constructor:
 Utility classes should not have a public or default constructor

 Final to reduce Visibility

 Duplicated Code?
 */

public final class FeatureTransformer {

	private FeatureTransformer() {

	}

	// FeatureRequestDTO -> FeatureEntity
	public static FeatureEntity toFeatureEntity(FeatureRequest featureRequest) {
		return FeatureEntity.builder()
			.password(featureRequest.password())
			.fullName(featureRequest.fullName())
			.build();
	}

	// FeatureResponseDTO -> FeatureEntity
	// Don't transform to DTO
	public static FeatureResponse toFeatureResponseDTO(FeatureEntity featureEntity) {
		return FeatureResponse.builder().id(featureEntity.getId()).fullName(featureEntity.getFullName()).build();
	}

	// Implement Required Business Rules
	// Update Table multiple Screen require additional Validations
	// (Entity , DTO)
	public static FeatureEntity update(FeatureEntity featureEntity, FeatureRequest featureRequestDTO) {
		// Create ValidationUtil is best practice according to how?

		if (featureRequestDTO.fullName() != null && !featureRequestDTO.fullName().isBlank()) {
			featureEntity.setFullName(featureRequestDTO.fullName());
		}

		if (featureRequestDTO.password() != null && !featureRequestDTO.password().isBlank()) {
			featureEntity.setPassword(featureRequestDTO.password());
		}

		return featureEntity;
	}

}

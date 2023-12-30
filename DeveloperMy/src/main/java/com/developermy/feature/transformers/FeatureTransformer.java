package com.developermy.feature.transformers;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;

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
	public static FeatureEntity toFeatureEntity(FeatureRequestDTO featureRequestDTO) {
		return FeatureEntity.builder()
			.password(featureRequestDTO.password())
			.fullName(featureRequestDTO.fullName())
			.build();
	}

	// FeatureResponseDTO -> FeatureEntity
	// Don't transform to DTO
	public static FeatureResponseDTO toFeatureResponseDTO(FeatureEntity featureEntity) {

		return FeatureResponseDTO.builder().id(featureEntity.getId()).fullName(featureEntity.getFullName()).build();
	}

	// Implement Required Business Rules
	// Update Table multiple Screen require additional Validations
	// (Entity , DTO)
	public static FeatureEntity update(FeatureEntity featureEntity, FeatureRequestDTO featureRequestDTO) {
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

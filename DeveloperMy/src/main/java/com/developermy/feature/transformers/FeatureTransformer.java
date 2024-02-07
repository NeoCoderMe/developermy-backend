package com.developermy.feature.transformers;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;

public final class FeatureTransformer {

	private FeatureTransformer() {

	}

	public static FeatureEntity toFeatureEntity(FeatureRequest featureRequest) {
		return FeatureEntity.builder()
			.password(featureRequest.password())
			.fullName(featureRequest.fullName())
			.build();
	}

	public static FeatureResponse toFeatureResponseDTO(FeatureEntity featureEntity) {
		return FeatureResponse.builder().id(featureEntity.getId()).fullName(featureEntity.getFullName()).build();
	}

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

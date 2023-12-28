package com.developermy.feature.transformers;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;

/*
 Hide Utility Class Constructor:
 Utility classes should not have a public or default constructor
 
 Final to reduce Visibility
 */

public final class FeatureTransformer {

	private FeatureTransformer() {
		
	}
	
	public static FeatureResponseDTO toResponseDTO(FeatureEntity featureEntity) {
		return new FeatureResponseDTO(featureEntity.getFullName());
	}
	
	public static FeatureEntity toFeatureEntity(FeatureRequestDTO featureRequestDTO) {
		
		return  FeatureEntity.builder()
				.password(featureRequestDTO.password())
				.fullName(featureRequestDTO.fullName())
				.build();
	} 
}

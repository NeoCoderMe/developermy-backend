package com.developermy.feature.services;

import org.springframework.stereotype.Service;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.providers.FeatureProvider;
import com.developermy.feature.transformers.FeatureTransformer;

/*
 Business logic
 Transformations
 */
@Service
public class FeatureService {

	private FeatureProvider featureProvider;

	public FeatureService(FeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	// elegance over performance
	public FeatureResponseDTO getFeature(Long id) {
		FeatureEntity featureEntity = featureProvider.getFeature(id);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponseDTO saveFeature(FeatureRequestDTO featureRequestDTO) {
		FeatureEntity featureEntity = FeatureTransformer.toFeatureEntity(featureRequestDTO);

		featureEntity = featureProvider.saveFeature(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponseDTO updateFeature(FeatureRequestDTO featureRequestDTO, Long id) {
		FeatureEntity featureEntity = featureProvider.getFeature(id);

		// Updating old values
		featureEntity = FeatureTransformer.update(featureEntity, featureRequestDTO);
		featureEntity = featureProvider.saveFeature(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

}

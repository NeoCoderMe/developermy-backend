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

	private final FeatureProvider featureProvider;

	public FeatureService(FeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	// elegance over performance
	public FeatureResponseDTO findById(Long id) {
		FeatureEntity featureEntity = featureProvider.findById(id);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponseDTO save(FeatureRequestDTO featureRequestDTO) {
		FeatureEntity featureEntity = FeatureTransformer.toFeatureEntity(featureRequestDTO);

		featureEntity = featureProvider.save(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponseDTO update(FeatureRequestDTO featureRequestDTO, Long id) {
		FeatureEntity featureEntity = featureProvider.findById(id);

		// Updating old values present
		featureEntity = FeatureTransformer.update(featureEntity, featureRequestDTO);
		featureEntity = featureProvider.save(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

}

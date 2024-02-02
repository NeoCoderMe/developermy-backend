package com.developermy.feature.services;

import org.springframework.stereotype.Service;

import com.developermy.feature.adapter.FeatureAdapter;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;
import com.developermy.feature.transformers.FeatureTransformer;

/*
 Business logic
 */
@Service
public class FeatureService {

	private final FeatureAdapter featureAdapter;

	public FeatureService(FeatureAdapter featureAdapter) {
		this.featureAdapter = featureAdapter;
	}

	// elegance over performance
	public FeatureResponse findById(Long id) {
		FeatureEntity featureEntity = featureAdapter.findById(id);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponse save(FeatureRequest featureRequest) {
		FeatureEntity featureEntity = FeatureTransformer.toFeatureEntity(featureRequest);

		featureEntity = featureAdapter.save(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

	public FeatureResponse update(FeatureRequest featureRequestDTO, Long id) {
		FeatureEntity featureEntity = featureAdapter.findById(id);

		// Updating old values present
		featureEntity = FeatureTransformer.update(featureEntity, featureRequestDTO);
		featureEntity = featureAdapter.save(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}

}

package com.developermy.feature.services;

import org.springframework.stereotype.Service;

import com.developermy.feature.adapter.FeatureAdapter;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;

/*
 Business logic
 */
@Service
public class FeatureService {

	private final FeatureAdapter featureAdapter;

	public FeatureService(FeatureAdapter featureAdapter) {
		this.featureAdapter = featureAdapter;
	}

	public FeatureResponse findById(Long id) {
		return featureAdapter.findById(id);
	}

	public FeatureResponse save(FeatureRequest featureRequest) {
		 
		return featureAdapter.save(featureRequest);
	}

	public FeatureResponse update(FeatureRequest featureRequestDTO, Long id) {
		return featureAdapter.update(featureRequestDTO, id);
	}

}

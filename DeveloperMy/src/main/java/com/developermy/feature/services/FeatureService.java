package com.developermy.feature.services;

import org.springframework.stereotype.Service;

import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.providers.FeatureProvider;

@Service
public class FeatureService {
	
	private FeatureProvider featureProvider;
	
	public FeatureService (FeatureProvider featureProvider) {
		this.featureProvider = featureProvider;
	}

	public FeatureResponseDTO getFeature(Long id) {
		return featureProvider.getFeature(id);
	}

	
}

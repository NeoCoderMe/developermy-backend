package com.developermy.feature.providers;

import org.springframework.stereotype.Service;

import com.developermy.crosscutting.exceptions.BadRequestException;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.repositories.FeatureRepository;

/*  Chapter 8: Boundaries

	Dependency Inversion Principle
	Database direct operations
*/

@Service
public class FeatureProvider {

	private FeatureRepository featureRepository;

	public FeatureProvider(FeatureRepository featureRepository) {
		this.featureRepository = featureRepository;
	}

	public FeatureEntity getFeature(Long id) {
		/*
		 * Don't create a new Instance Return immediately
		 */
		return featureRepository.findById(id).orElseThrow(() -> new BadRequestException("Feature Id not found " + id));

	}

	public FeatureEntity saveFeature(FeatureEntity featureEntity) { // INPUT Entity

		return featureRepository.save(featureEntity);
	}

}

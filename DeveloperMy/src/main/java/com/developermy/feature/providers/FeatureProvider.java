package com.developermy.feature.providers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developermy.crosscutting.exceptions.BadRequestException;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;
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

	public FeatureEntity findById(Long id) {
		/*
		 * Don't create a new Instance Return immediately
		 */
		return featureRepository.findById(id).orElseThrow(() -> new BadRequestException("Feature Id not found " + id));

	}

	public FeatureEntity save(FeatureEntity featureEntity) { // INPUT Entity

		return featureRepository.save(featureEntity);
	}

	public List<FeatureEntity> findAllFeatures() {
		return featureRepository.findAllFeatures();
	}

	public List<FeatureBasicInfo> findIdAndFullName() {
		return featureRepository.findIdAndFullName();
	}

}

package com.developermy.feature.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developermy.crosscutting.exceptions.NotFoundException;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;
import com.developermy.feature.repositories.FeatureRepository;

/*  Chapter 8: Boundaries

	Dependency Inversion Principle
	Database direct operations
*/

@Service
public class FeatureAdapter {

	private final FeatureRepository featureRepository;

	public FeatureAdapter(FeatureRepository featureRepository) {
		this.featureRepository = featureRepository;
	}

	public FeatureEntity findById(Long id) {
		/*
		 * Don't create a new Instance Return immediately
		 */
		return featureRepository.findById(id).orElseThrow(() -> new NotFoundException("Feature Id not found " + id));

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

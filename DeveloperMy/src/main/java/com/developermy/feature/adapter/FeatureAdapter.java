package com.developermy.feature.adapter;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;
import com.developermy.feature.models.natives.FeatureBasicInfo;
import com.developermy.feature.repositories.FeatureRepository;
import com.developermy.feature.transformers.FeatureTransformer;

import jakarta.persistence.EntityNotFoundException;

/*  Chapter 8: Boundaries

	Dependency Inversion Principle
	Database direct operations
	
	Transformation
*/
 
@Service
public class FeatureAdapter {

	private final FeatureRepository featureRepository;

	public FeatureAdapter(FeatureRepository featureRepository) {
		this.featureRepository = featureRepository;
	}

	public FeatureResponse findById(Long id) {
		
		FeatureEntity featureEntity = findEntityById(id);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}
	
	public FeatureResponse update(FeatureRequest featureRequestDTO, Long id) {
		FeatureEntity featureEntity = findEntityById(id);
		
		// Updating old values present
		featureEntity = FeatureTransformer.update(featureEntity, featureRequestDTO);
		featureEntity = featureRepository.save(featureEntity);
		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}
 
	public FeatureResponse save(FeatureRequest featureRequest) { 
		
		FeatureEntity featureEntity = FeatureTransformer.toFeatureEntity(featureRequest);
		featureEntity = featureRepository.save(featureEntity);

		return FeatureTransformer.toFeatureResponseDTO(featureEntity);
	}
	
	public List<FeatureEntity> findAllFeatures() {
		return featureRepository.findAllFeatures();
	}

	public List<FeatureBasicInfo> findIdAndFullName() {
		return featureRepository.findIdAndFullName();
	}
	
	private FeatureEntity findEntityById(Long id) {
		return featureRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Feature Id not found " + id));
	}
}

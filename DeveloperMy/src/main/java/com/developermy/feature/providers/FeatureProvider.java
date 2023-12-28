package com.developermy.feature.providers;

import org.springframework.stereotype.Service;

import com.developermy.crosscutting.exceptions.BadRequestException;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.repositories.FeatureRepository;
import com.developermy.feature.transformers.FeatureTransformer;

/*  Chapter 8: Boundaries

	Dependency Inversion Principle
	Database direct operations
	Transformation
*/

@Service
public class FeatureProvider {

	private FeatureRepository featureRepository;
	
	public FeatureProvider(FeatureRepository featureRepository) {
		this.featureRepository = featureRepository  ;
	}
	

	public FeatureResponseDTO getFeature(Long id)  {
		FeatureEntity featureEntity= featureRepository.findById(id)
				.orElseThrow( () -> new BadRequestException("Feature Id not found " +id ));
		
		/* Don't create a new Instance Return immediately
		FeatureResponseDTO response = FeatureTransformer.toResponseDTO(featureEntity);
		return response; */
		
		return FeatureTransformer.toResponseDTO(featureEntity);
	}
}

package com.developermy.feature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developermy.feature.models.FeatureEntity;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {
	
}
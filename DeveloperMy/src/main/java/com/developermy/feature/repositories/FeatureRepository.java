package com.developermy.feature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Long> {

	@Query(value = "SELECT * FROM feature where id > 0", nativeQuery = true)
	List<FeatureEntity> findAllFeatures();

	@Query(value = "SELECT id, full_name FROM feature where id > 0", nativeQuery = true)
	List<FeatureBasicInfo> findIdAndFullName();

}
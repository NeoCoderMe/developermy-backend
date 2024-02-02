package com.developermy.feature.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;

@DataJpaTest
class FeatureRepositoryTest {

	@Autowired
	private FeatureRepository featureRepository;

	@Test
	@Sql("/db/features.sql")
	void test_find_all_features_expected_ok() {

		List<FeatureEntity> features = featureRepository.findAllFeatures();

		assertNotNull(features);
		assertEquals(3, features.size());
	}

	@Test
	@Sql("/db/features.sql")
	public void test_find_id_and_full_name_expected_ok() {
		List<FeatureBasicInfo> results = featureRepository.findIdAndFullName();

		assertNotNull(results);
		assertEquals(3, results.size());

		for (FeatureBasicInfo result : results) {
			assertNotNull(result);
			System.out.println(result);
			System.out.println(result.getFull_Name());

			assertNotNull(result.getId());
			assertNotNull(result.getFull_Name());
		}
	}

}

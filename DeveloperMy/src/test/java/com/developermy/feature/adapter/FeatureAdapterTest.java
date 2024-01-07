package com.developermy.feature.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;
import com.developermy.feature.repositories.FeatureRepository;
import com.developermy.feature.utils.FeatureUtilTest;

@ExtendWith(MockitoExtension.class)
class FeatureAdapterTest {

	private FeatureAdapter featureAdapter;

	@Mock
	private FeatureRepository featureRepository;

	@BeforeEach
	void setUp() {
		Mockito.reset(featureRepository);
		featureAdapter = new FeatureAdapter(featureRepository);
	}

	@Test
    void test_find_all_features_expected_ok() {
		when(featureRepository.findAllFeatures()).thenReturn(FeatureUtilTest.getFeatureEntityList());
		
        List<FeatureEntity> features = featureAdapter.findAllFeatures();

        assertNotNull(features);
        assertEquals(2, features.size());
    }

	@Test
    void test_find_id_and_full_name_expected_ok() {
		when(featureRepository.findIdAndFullName()).thenReturn(FeatureUtilTest.getFeatureBasicInfo());
		
        List<FeatureBasicInfo> results = featureAdapter.findIdAndFullName();

        assertNotNull(results);
        assertEquals(2, results.size());

        for (FeatureBasicInfo result : results) {
            assertNotNull(result);

            assertNotNull(result.getFull_Name());
            assertNotNull(result.getId());
        }
	}

	@Test
	void test_find_features_by_id_expected_ok() {
		Optional<FeatureEntity> feature = Optional.of(FeatureUtilTest.getFeatureEntity1());
		when(featureRepository.findById(1l)).thenReturn(feature);

		FeatureEntity features = featureAdapter.findById(1l); // Don't returning optional
																// because this throws an
																// exception

		assertNotNull(features);
		assertNotNull(features.getFullName());
	}

}

package com.developermy.feature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.developermy.feature.adapter.FeatureAdapter;
import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest No needed
class FeatureServiceTest {

	@Mock
	private FeatureAdapter featureAdapter;

	@InjectMocks
	private FeatureService featureService;

	@Test
	void get_feature_expected_ok() {
		Long featureId = 1L;
		FeatureEntity mockFeatureEntity = FeatureEntity.builder()
			.id(1l)
			.password("123456")
			.fullName("John Doe")
			.build();

		FeatureResponse expectedResponse = FeatureResponse.builder().id(1l).fullName("John Doe").build();

		when(featureAdapter.findById(featureId)).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.findById(featureId);

		verify(featureAdapter, times(1)).findById(featureId);
		assertEquals(expectedResponse, result);
	}

	@Test
	void save_feature_expected_ok() {
		FeatureRequest requestDTO = new FeatureRequest("John Doe", "secretpassword");
		FeatureEntity mockFeatureEntity = FeatureEntity.builder()
			.id(1l)
			.password("123456")
			.fullName("John Doe")
			.build();

		when(featureAdapter.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.save(requestDTO);
		FeatureResponse expectedResponse = FeatureResponse.builder().id(1l).fullName("John Doe").build();

		verify(featureAdapter, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

	@Test
	void updateFeature_expected_ok() {
		Long featureId = 1L;
		FeatureResponse expectedResponse = FeatureResponse.builder().id(1l).fullName("John Doe").build();
		FeatureRequest requestDTO = new FeatureRequest("John Doe", "secretpassword");
		FeatureEntity mockFeatureEntity = FeatureEntity.builder()
			.id(1l)
			.password("123456")
			.fullName("John Doe")
			.build();

		when(featureAdapter.findById(featureId)).thenReturn(mockFeatureEntity);
		when(featureAdapter.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.update(requestDTO, featureId);

		verify(featureAdapter, times(1)).findById(featureId);
		verify(featureAdapter, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

}

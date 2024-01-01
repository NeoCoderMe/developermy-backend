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

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.providers.FeatureProvider;

@ExtendWith(MockitoExtension.class)
// @SpringBootTest No needed
class FeatureServiceTest {

	@Mock
	private FeatureProvider featureProvider;

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

		FeatureResponseDTO expectedResponse = FeatureResponseDTO.builder().id(1l).fullName("John Doe").build();

		when(featureProvider.findById(featureId)).thenReturn(mockFeatureEntity);

		FeatureResponseDTO result = featureService.findById(featureId);

		verify(featureProvider, times(1)).findById(featureId);
		assertEquals(expectedResponse, result);
	}

	@Test
	void save_feature_expected_ok() {
		FeatureRequestDTO requestDTO = new FeatureRequestDTO("John Doe", "secretpassword");
		FeatureEntity mockFeatureEntity = FeatureEntity.builder()
			.id(1l)
			.password("123456")
			.fullName("John Doe")
			.build();

		when(featureProvider.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponseDTO result = featureService.save(requestDTO);
		FeatureResponseDTO expectedResponse = FeatureResponseDTO.builder().id(1l).fullName("John Doe").build();

		verify(featureProvider, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

	@Test
	void updateFeature_expected_ok() {
		Long featureId = 1L;
		FeatureResponseDTO expectedResponse = FeatureResponseDTO.builder().id(1l).fullName("John Doe").build();
		FeatureRequestDTO requestDTO = new FeatureRequestDTO("John Doe", "secretpassword");
		FeatureEntity mockFeatureEntity = FeatureEntity.builder()
			.id(1l)
			.password("123456")
			.fullName("John Doe")
			.build();

		when(featureProvider.findById(featureId)).thenReturn(mockFeatureEntity);
		when(featureProvider.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponseDTO result = featureService.update(requestDTO, featureId);

		verify(featureProvider, times(1)).findById(featureId);
		verify(featureProvider, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

}

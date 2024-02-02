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
import com.developermy.feature.suppliers.FeatureEntitySupplier;
import com.developermy.feature.suppliers.FeatureRequestSupplier;
import com.developermy.feature.suppliers.FeatureResponseSupplier;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {

	@Mock
	private FeatureAdapter featureAdapter;

	@InjectMocks
	private FeatureService featureService;

	@Test
	void get_feature_expected_ok() {
		Long featureId = 1L;
		FeatureEntity mockFeatureEntity = FeatureEntitySupplier.getFeatureEntityAdmin();

		FeatureResponse expectedResponse = FeatureResponseSupplier.getFeatureResponse();

		when(featureAdapter.findById(featureId)).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.findById(featureId);

		verify(featureAdapter, times(1)).findById(featureId);
		assertEquals(expectedResponse, result);
	}

	@Test
	void save_feature_expected_ok() {
		FeatureRequest requestDTO = FeatureRequestSupplier.getFeatureRequest();
		FeatureEntity mockFeatureEntity = FeatureEntitySupplier.getFeatureEntityAdmin();

		when(featureAdapter.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.save(requestDTO);
		FeatureResponse expectedResponse = FeatureResponseSupplier.getFeatureResponse();

		verify(featureAdapter, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

	@Test
	void updateFeature_expected_ok() {
		Long featureId = 1L;
		FeatureResponse expectedResponse = FeatureResponseSupplier.getFeatureResponse();
		FeatureRequest requestDTO = FeatureRequestSupplier.getFeatureRequest();
		FeatureEntity mockFeatureEntity = FeatureEntitySupplier.getFeatureEntityAdmin();

		when(featureAdapter.findById(featureId)).thenReturn(mockFeatureEntity);
		when(featureAdapter.save(any(FeatureEntity.class))).thenReturn(mockFeatureEntity);

		FeatureResponse result = featureService.update(requestDTO, featureId);

		verify(featureAdapter, times(1)).findById(featureId);
		verify(featureAdapter, times(1)).save(any(FeatureEntity.class));
		assertEquals(expectedResponse, result);
	}

}

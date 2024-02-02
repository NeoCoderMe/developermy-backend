package com.developermy.feature.suppliers;

import com.developermy.feature.models.FeatureResponse;

public final class FeatureResponseSupplier {

	private FeatureResponseSupplier() {

	}

	public static FeatureResponse getFeatureResponse() {
		return FeatureResponse.builder().id(1l).fullName("Admin").build();
	}

}

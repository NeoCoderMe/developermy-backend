package com.developermy.feature.suppliers;

import com.developermy.feature.models.FeatureRequest;

public final class FeatureRequestSupplier {

	private FeatureRequestSupplier() {

	}

	public static FeatureRequest getFeatureRequest() {
		return new FeatureRequest("Admin", "12345");
	}

}

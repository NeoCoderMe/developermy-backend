package com.developermy.feature.utils;

import java.util.List;

import com.developermy.feature.models.FeatureEntity;
import com.developermy.feature.models.natives.FeatureBasicInfo;

//Private all the method until needed
public final class FeatureUtilTest {

	private FeatureUtilTest() {

	}

	public static List<FeatureEntity> getFeatureEntityList() {
		return List.of(getFeatureEntity1(), getFeatureEntity2());
	}

	public static List<FeatureBasicInfo> getFeatureBasicInfo() {
		return List.of(getFeatureBasicInfo1(), getFeatureBasicInfo2());
	}

	public static FeatureEntity getFeatureEntity1() {
		return FeatureEntity.builder().id(1l).fullName("John Doe").password("password123").build();
	}

	private static FeatureEntity getFeatureEntity2() {
		return FeatureEntity.builder().id(2l).fullName("Asus User").password("password3").build();
	}

	private static FeatureBasicInfo getFeatureBasicInfo1() {
		return new FeatureBasicInfo() {
			private Long id = 1L;

			private String full_name = "John Doe";

			public Long getId() {
				return id;
			}

			public String getFull_Name() {
				return full_name;
			}

		};
	}

	private static FeatureBasicInfo getFeatureBasicInfo2() {
		return new FeatureBasicInfo() {
			private Long id = 2L;

			private String full_name = "Other User";

			public Long getId() {
				return id;
			}

			public String getFull_Name() {
				return full_name;
			}

		};
	}

}

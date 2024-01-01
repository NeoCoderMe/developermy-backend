package com.developermy.feature.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "feature")
@NoArgsConstructor
@AllArgsConstructor
// org.hibernate.InstantiationException: No default constructor for entity
// 'com.developermy.feature.models.FeatureEntity'
public class FeatureEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name") // use underscore in the name
	private String fullName;

	@Column(name = "password")
	private String password;

}

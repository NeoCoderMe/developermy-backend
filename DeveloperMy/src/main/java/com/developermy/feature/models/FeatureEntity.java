package com.developermy.feature.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "features")
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "full_name") // use underscore in the name
	private String fullName;

	@Column(name = "password")
	private String password;

}

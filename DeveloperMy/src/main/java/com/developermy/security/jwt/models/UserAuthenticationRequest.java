package com.developermy.security.jwt.models;

import java.util.Set;

import com.developermy.crosscutting.models.AuthoritiesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationRequest {

	private String fullName;

	private String email;

	private Set<AuthoritiesDTO> authorities;

}

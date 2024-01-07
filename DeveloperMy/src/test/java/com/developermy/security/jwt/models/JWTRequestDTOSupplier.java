package com.developermy.security.jwt.models;

import com.developermy.crosscutting.models.AuthoritiesDTOSupplier;

public final class JWTRequestDTOSupplier {

	private JWTRequestDTOSupplier() {

	}

	public static UserAuthenticationRequest getJWTRequestDTOAdmin() {
		return UserAuthenticationRequest.builder()
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email1@hotmail.com")
			.build();
	}

	public static UserAuthenticationRequest getJWTRequestDTODeveloper() {
		return UserAuthenticationRequest.builder()
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email2@hotmail.com")
			.build();
	}

}

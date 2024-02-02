package com.developermy.security.jwt.suppliers;

import com.developermy.security.models.UserAuthenticationRequest;

public final class UserAuthenticationRequestSupplier {

	private UserAuthenticationRequestSupplier() {

	}

	public static UserAuthenticationRequest getJWTRequestDTOAdmin() {
		return new UserAuthenticationRequest("Admin", "1234");
	}

	public static UserAuthenticationRequest getJWTRequestDTODeveloper() {
		return new UserAuthenticationRequest("Developer", "1234");
	}

}

package com.developermy.security.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.developermy.security.jwt.suppliers.UserResponseSupplier;
import com.developermy.security.models.UserResponse;

class JwtServiceTest {

	private final JwtService jwtService = new JwtService();

	@Test
	void is_valid_token_expected_ok() {
		UserResponse userAdmin = UserResponseSupplier.getUserResponseAdmin();
		String tokenAdmin = jwtService.generateJWT(userAdmin);

		UserResponse userDev = UserResponseSupplier.getUserResponseUser();
		String tokenDev = jwtService.generateJWT(userDev);

		boolean isValid1 = jwtService.isValidToken(tokenAdmin);
		boolean isValid2 = jwtService.isValidToken(tokenDev);
		assertTrue(isValid1);
		assertTrue(isValid2);
	}

	@Test
	void is_valid_token_expected_error_invalid_token() {
		String jwt = "invalidJWT";
		String jwt2 = "jw";

		boolean isValid1 = jwtService.isValidToken(jwt);
		boolean isValid2 = jwtService.isValidToken(jwt2);
		assertFalse(isValid1);
		assertFalse(isValid2);
	}

}

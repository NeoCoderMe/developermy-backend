package com.developermy.security.jwt;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.developermy.security.jwt.models.UserAuthenticationRequest;
import com.developermy.security.jwt.models.JWTRequestDTOSupplier;
import com.developermy.security.jwt.utils.JWTUtil;

import io.jsonwebtoken.security.InvalidKeyException;

class JWTValidatorTest {

	@Test
	void is_valid_token_for_email1() throws InvalidKeyException, Exception {
		UserAuthenticationRequest user1 = JWTRequestDTOSupplier.getJWTRequestDTOAdmin();
		String token1 = JWTUtil.generateJWT(user1);

		UserAuthenticationRequest user2 = JWTRequestDTOSupplier.getJWTRequestDTODeveloper();
		String token2 = JWTUtil.generateJWT(user2);
		boolean isValid1 = JWTUtil.isValidToken(token1);
		boolean isValid2 = JWTUtil.isValidToken(token2);
		assertTrue(isValid1);
		assertTrue(isValid2);
	}

}

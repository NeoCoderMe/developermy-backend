package com.developermy.security.jwt.services;


import com.developermy.security.jwt.models.SecurityTokenDTO;

import org.springframework.stereotype.Service;

import com.developermy.security.jwt.models.JwtVerificationResponse;
import com.developermy.security.jwt.models.UserAuthenticationRequest;
import com.developermy.security.jwt.utils.JWTUtil;

import io.jsonwebtoken.JwtException;

@Service
public class JwtService {
	
	 

	public SecurityTokenDTO generateJWT(UserAuthenticationRequest jwtRequestDTO) {
		// TODO is valid user and password

		try {
			return SecurityTokenDTO.builder().jwt(JWTUtil.generateJWT(jwtRequestDTO)).build();
		}
		catch (Exception e) {
			throw new JwtException("Invalid JWT ", e);
		}
		
	}

	public JwtVerificationResponse isValid(SecurityTokenDTO jwtRequestDTO) {

		return JwtVerificationResponse.builder().isValid(JWTUtil.isValidToken(jwtRequestDTO.getJwt())).build();
	}

}

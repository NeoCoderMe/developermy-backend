package com.developermy.security.services;

import org.springframework.stereotype.Service;

import com.developermy.security.adapter.UserAdapter;
import com.developermy.security.models.SecurityTokenDTO;
import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.models.UserResponse;

@Service
public class UserService {

	private final UserAdapter loginAdapter;

	private final JwtService jwtService;

	public UserService(UserAdapter loginAdapter, JwtService jwtService) {
		this.loginAdapter = loginAdapter;
		this.jwtService = jwtService;
	}

	public SecurityTokenDTO loginUser(UserAuthenticationRequest userAuthenticationRequest) {
		UserResponse userResponse = loginAdapter.findUser(userAuthenticationRequest);
		return SecurityTokenDTO.builder()
			.jwt(jwtService.generateJWT(userResponse))
			.userName(userAuthenticationRequest.userName())
			.build();
	}

	public String decode(String authorizationHeader) {
		return jwtService.decodeJwtToken(authorizationHeader).getSubject();

	}

}
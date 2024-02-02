package com.developermy.security.adapter;

import org.springframework.stereotype.Service;

import com.developermy.security.models.UserAuthenticationRequest;
import com.developermy.security.models.UserEntity;
import com.developermy.security.models.UserResponse;
import com.developermy.security.repositories.UserRepository;
import com.developermy.security.transformers.UserTransformer;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserAdapter {

	private final UserRepository loginRepository;

	public UserAdapter(UserRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public UserResponse findUser(UserAuthenticationRequest userAuthenticationRequest) {

		UserEntity userEntity = loginRepository
			.findByUserNameAndPassword(userAuthenticationRequest.userName(), userAuthenticationRequest.password())
			.orElseThrow(() -> new EntityNotFoundException("Authentication failed! Please try again..."));

		return UserTransformer.toUserResponse(userEntity);
	}

}
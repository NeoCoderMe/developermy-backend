package com.developermy.security.jwt.suppliers;

import java.util.Set;

import com.developermy.security.enums.AuthorityEnum;
import com.developermy.security.models.UserResponse;

public final class UserResponseSupplier {

	private UserResponseSupplier() {

	}

	public static UserResponse getUserResponseAdmin() {
		return UserResponse.builder().id(1l).authorities(Set.of(AuthorityEnum.ADMIN)).userName("Admin").build();
	}

	public static UserResponse getUserResponseUser() {
		return UserResponse.builder().id(1l).authorities(Set.of(AuthorityEnum.USER)).userName("Admin").build();
	}

}

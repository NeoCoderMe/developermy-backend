package com.developermy.security.jwt.suppliers;

import com.developermy.security.models.UserEntity;

public final class UserEntitySupplier {

	private UserEntitySupplier() {

	}

	public static UserEntity getUserEntitySupplier() {
		return UserEntity.builder().email("email.com").id(1l).password("123").userName("admin").build();
	}

}

package com.developermy.crosscutting.suppliers;

import java.util.Set;

import com.developermy.crosscutting.models.UserSsoDTO;
import com.developermy.security.enums.AuthorityEnum;

public final class UserDTOSupplier {

	private UserDTOSupplier() {

	}

	public static UserSsoDTO getUserDTOAdmin() {
		return UserSsoDTO.builder()
			.id(1l)
			.authorities(Set.of(AuthorityEnum.ADMIN))
			.email("email1@hotmail.com")
			.fullName("user 1")
			.enabled(true)
			.password("1234")
			.urlPicture("google.com")
			.build();
	}

	public static UserSsoDTO getUserDTODeveloper() {
		return UserSsoDTO.builder()
			.id(1l)
			.authorities(Set.of(AuthorityEnum.USER))
			.email("email2@hotmail.com")
			.fullName("user 2")
			.enabled(true)
			.password("1234")
			.urlPicture("google.com")
			.build();
	}

}

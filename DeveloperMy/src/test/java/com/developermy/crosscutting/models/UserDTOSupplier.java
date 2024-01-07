package com.developermy.crosscutting.models;

public final class UserDTOSupplier {

	private UserDTOSupplier() {

	}

	public static UserDTO getUserDTOAdmin() {
		return UserDTO.builder()
			.id(1l)
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email1@hotmail.com")
			.fullName("user 1")
			.enabled(true)
			.password("1234")
			.urlPicture("google.com")
			.build();
	}

	public static UserDTO getUserDTODeveloper() {
		return UserDTO.builder()
			.id(1l)
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email2@hotmail.com")
			.fullName("user 2")
			.enabled(true)
			.password("1234")
			.urlPicture("google.com")
			.build();
	}

}

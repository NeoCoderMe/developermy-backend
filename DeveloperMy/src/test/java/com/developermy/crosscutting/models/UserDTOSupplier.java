package com.developermy.crosscutting.models;

public final class UserDTOSupplier {

	private UserDTOSupplier() {

	}

	public static UserDTO getUserDTOAdmin() {
		return UserDTO.builder()
			.id(1l)
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email1@hotmail.com")
			.build();
	}

	public static UserDTO getUserDTODeveloper() {
		return UserDTO.builder()
			.id(1l)
			.authorities(AuthoritiesDTOSupplier.getAuthorityDTOAdminSet())
			.email("email2@hotmail.com")
			.build();
	}

}

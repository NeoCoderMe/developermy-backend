package com.developermy.crosscutting.models;

import java.util.Set;

import com.developermy.crosscutting.enums.AuthorityEnum;

public final class AuthoritiesDTOSupplier {

	private AuthoritiesDTOSupplier() {

	}

	public static AuthoritiesDTO getAuthorityDTOAdmin() {
		return AuthoritiesDTO.builder().id(1l).authority(AuthorityEnum.ROLE_ADMIN).build();
	}

	public static AuthoritiesDTO getAuthorityDTOUser() {
		return AuthoritiesDTO.builder().id(2l).authority(AuthorityEnum.ROLE_USER).build();
	}

	public static Set<AuthoritiesDTO> getAuthorityDTOAdminSet() {
		return Set.of(getAuthorityDTOAdmin());
	}

	public static Set<AuthoritiesDTO> getAuthorityDTOUserSet() {
		return Set.of(getAuthorityDTOUser());
	}

}

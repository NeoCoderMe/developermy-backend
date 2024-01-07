package com.developermy.crosscutting.models;

import com.developermy.crosscutting.enums.AuthorityEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesDTO {

	// implements GrantedAuthority

	private Long id;

	private AuthorityEnum authority;

}

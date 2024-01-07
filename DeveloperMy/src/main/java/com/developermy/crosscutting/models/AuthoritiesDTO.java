package com.developermy.crosscutting.models;

import java.io.Serial;
import java.io.Serializable;

import com.developermy.crosscutting.enums.AuthorityEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesDTO implements Serializable {

	// implements GrantedAuthority

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	private AuthorityEnum authority;

}

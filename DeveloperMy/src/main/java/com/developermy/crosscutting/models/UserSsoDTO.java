package com.developermy.crosscutting.models;

import java.io.Serializable;
import java.util.Set;

import com.developermy.security.enums.AuthorityEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSsoDTO implements Serializable {

	private static final long serialVersionUID = 5321372543651919567L;

	private Long id;

	private String userName;

	private String password;

	private String urlPicture;

	private String fullName;

	private Boolean enabled;

	private String email;

	private Set<AuthorityEnum> authorities;

}

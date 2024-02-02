package com.developermy.security.models;

import java.util.Set;

import com.developermy.security.enums.AuthorityEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

	private Long id;

	private String userName;

	private Set<AuthorityEnum> authorities;

}

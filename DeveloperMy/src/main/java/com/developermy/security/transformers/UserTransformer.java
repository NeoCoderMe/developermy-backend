package com.developermy.security.transformers;

import java.util.Set;

import com.developermy.security.enums.AuthorityEnum;
import com.developermy.security.models.UserEntity;
import com.developermy.security.models.UserResponse;

public final class UserTransformer {

	private UserTransformer() {

	}

	public static UserResponse toUserResponse(UserEntity userEntity) {
		return UserResponse.builder()
			.id(userEntity.getId())
			.userName(userEntity.getUserName())
			.authorities(Set.of(AuthorityEnum.USER))
			.build();
	}

}

package com.developermy.security.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.developermy.security.models.UserEntity;

@DataJpaTest
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Sql("/db/users.sql")
	void test_find_all_features_expected_ok() {

		List<UserEntity> features = userRepository.findAll();

		assertNotNull(features);
		assertEquals(10, features.size());
	}

	@Test
	@Sql("/db/users.sql")
	void test2_find_all_features_expected_ok() {

		Optional<UserEntity> user = userRepository.findByUserNameAndPassword("john_doe", "password123");

		assertNotNull(user);
		assertTrue(user.isPresent());
		assertEquals("john_doe", user.get().getUserName());
	}

}

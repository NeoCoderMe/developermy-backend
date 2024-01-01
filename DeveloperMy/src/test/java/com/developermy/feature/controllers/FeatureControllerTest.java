package com.developermy.feature.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.developermy.crosscutting.exceptions.BadRequestException;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.services.FeatureService;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 Mocking because
 Each test needs to be independent
 can run in a parallel way
 */

@WebMvcTest
class FeatureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FeatureService featureService;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		Mockito.reset(featureService);
	}

	@Test
	void save_feature_expected_ok() throws Exception {
		FeatureRequestDTO requestDTO = new FeatureRequestDTO("John Doe", "secretpassword");
		FeatureResponseDTO responseDTO = FeatureResponseDTO.builder().id(1l).fullName("John Doe").build();

		when(featureService.save(requestDTO)).thenReturn(responseDTO);

		// POST
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/feature/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(requestDTO));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void get_feature_expected_ok() throws Exception {
		Long featureId = 1L;
		FeatureResponseDTO responseDTO = FeatureResponseDTO.builder().id(featureId).fullName("John Doe").build();

		when(featureService.findById(featureId)).thenReturn(responseDTO);

		// GET
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feature/{id}", featureId)
			.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void update_feature_expected_ok() throws Exception {
		Long featureId = 1L;
		FeatureRequestDTO requestDTO = new FeatureRequestDTO("Updated Name", "Updated Password");
		FeatureResponseDTO responseDTO = FeatureResponseDTO.builder().id(1l).fullName("Updated Name").build();

		when(featureService.update(requestDTO, featureId)).thenReturn(responseDTO);

		// PATCH
		RequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/feature/{id}", featureId)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(requestDTO));

		mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
	}

	@Test
	void get_feature_expected_error() throws Exception {
		Long featureId = 1L;

		when(featureService.findById(featureId))
			.thenThrow(new BadRequestException("Feature Id not found " + featureId));

		// GET
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/feature/{id}", featureId)
			.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest()).andReturn();
	}

}

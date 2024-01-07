package com.developermy.feature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.crosscutting.controllers.BaseController;
import com.developermy.crosscutting.models.GenericErrorMessageDTO;
import com.developermy.crosscutting.models.GenericResponse;
import com.developermy.feature.models.FeatureRequest;
import com.developermy.feature.models.FeatureResponse;
import com.developermy.feature.services.FeatureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//{"full_name":"Eliseo", "password":"1234556"}
@Tag(name = "Feature2", description = "Documentation APIs Example")
@Validated
@RestController
@RequestMapping("/feature2")
public class FeatureController2 extends BaseController {

	private final FeatureService featureService;

	public FeatureController2(FeatureService featureService) {
		this.featureService = featureService;
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Create a new feature",
			description = "Saves a feature given its details and returns the created feature information."
	// ,tags = { "Create" }
	)
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Feature created successfully",
					content = { @Content(schema = @Schema(implementation = FeatureResponse.class),
							mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }) })

	public ResponseEntity<GenericResponse<FeatureResponse>> save(
			@RequestBody @Parameter(description = "Feature details for creation") FeatureRequest featureRequestDTO) {
		return buildOkResponse(featureService.save(featureRequestDTO));
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Retrieve feature by ID", description = "Retrieves feature details based on the provided ID."
	// ,tags = { "Retrieve" }
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Feature retrieved successfully",
					content = { @Content(schema = @Schema(implementation = FeatureResponse.class),
							mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Feature not found",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }) })
	public ResponseEntity<GenericResponse<FeatureResponse>> findById(
			@Parameter(name = "id", description = "ID of the feature to be retrieved", in = ParameterIn.PATH,
					required = true, example = "1") @PathVariable Long id) {
		return buildOkResponse(featureService.findById(id));
	}

	/*
	 * PUT: Used to update a resource or create a new resource if it does not exist.
	 * PATCH: Used to apply partial modifications to a resource.
	 */
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Operation(summary = "Update feature by ID",
			description = "Updates feature details based on the provided ID and request body."
	// ,tags = { "Update" }
	)
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "Feature updated successfully",
					content = { @Content(schema = @Schema(implementation = FeatureResponse.class),
							mediaType = "application/json") }),
			@ApiResponse(responseCode = "400", description = "Invalid request data",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
			@ApiResponse(responseCode = "404", description = "Feature not found",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Internal server error",
					content = { @Content(schema = @Schema(implementation = GenericErrorMessageDTO.class)) }) })
	public ResponseEntity<GenericResponse<FeatureResponse>> update(
			@RequestBody @Parameter(description = "Updated feature details") FeatureRequest featureRequestDTO,
			@Parameter(description = "ID of the feature to update") @PathVariable("id") Long id) {
		return buildOkResponse(featureService.update(featureRequestDTO, id));
	}

}

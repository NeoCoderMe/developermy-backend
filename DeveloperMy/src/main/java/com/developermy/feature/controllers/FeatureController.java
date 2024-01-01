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
import com.developermy.crosscutting.models.GenericResponseDTO;
import com.developermy.feature.models.FeatureRequestDTO;
import com.developermy.feature.models.FeatureResponseDTO;
import com.developermy.feature.services.FeatureService;

//{"full_name":"John", "password":"1234556"}
@Validated
@RestController
@RequestMapping("/feature")
public class FeatureController extends BaseController {

	private FeatureService featureService;

	public FeatureController(FeatureService featureService) {
		this.featureService = featureService;
	}

	// All named save using spring naming
	// save over create because we use the save method also for update
	@PostMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> save(
			@RequestBody FeatureRequestDTO featureRequestDTO) {
		return buildOkResponse(featureService.save(featureRequestDTO));
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> findById(@PathVariable Long id) {
		return buildOkResponse(featureService.findById(id));
	}

	/*
	 * PUT: Used to update a resource or create a new resource if it does not exist.
	 * PATCH: Used to apply partial modifications to a resource.
	 */
	@PatchMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<GenericResponseDTO<FeatureResponseDTO>> update(
			@RequestBody FeatureRequestDTO featureRequestDTO, @PathVariable("id") Long id) {
		return buildOkResponse(featureService.update(featureRequestDTO, id));
	}

}

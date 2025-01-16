package com.fullstack.shop.web.routine.controller;

import com.fullstack.shop.web.routine.dto.APIResponse;
import com.fullstack.shop.web.routine.dto.request.AuthenticationRequestDTO;
import com.fullstack.shop.web.routine.dto.request.IntrospectRequestDTO;
import com.fullstack.shop.web.routine.dto.response.AuthenticationResponseDTO;
import com.fullstack.shop.web.routine.dto.response.IntrospectResponseDTO;
import com.fullstack.shop.web.routine.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
	AuthenticationService authenticationService;

	@PostMapping("/token")
	public APIResponse<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) {
		var result = authenticationService.authenticate(authenticationRequestDTO);
		return APIResponse.<AuthenticationResponseDTO>builder()
				.result(result)
				.build();
	}

	@PostMapping("/introspect")
	public APIResponse<IntrospectResponseDTO> authenticate(@RequestBody IntrospectRequestDTO requestDTO) {
		var result = authenticationService.introspect(requestDTO);
		return APIResponse.<IntrospectResponseDTO>builder()
				.result(result)
				.build();
	}
}

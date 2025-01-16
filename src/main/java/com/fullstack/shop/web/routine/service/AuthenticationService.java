package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.AuthenticationRequestDTO;
import com.fullstack.shop.web.routine.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
	UserRepository userRepository;

	public boolean authenticate(AuthenticationRequestDTO request) {
		var user = userRepository.findByEmail(request.getUsernameOrEmail())
				.orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		return passwordEncoder.matches(request.getPassword(), user.getPassword());
	}
}

package com.fullstack.shop.web.routine.service;

import com.fullstack.shop.web.routine.dto.request.AuthenticationRequestDTO;
import com.fullstack.shop.web.routine.dto.request.IntrospectRequestDTO;
import com.fullstack.shop.web.routine.dto.response.AuthenticationResponseDTO;
import com.fullstack.shop.web.routine.dto.response.IntrospectResponseDTO;
import com.fullstack.shop.web.routine.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
	UserRepository userRepository;
	@NonFinal
	static String SIGNER_KEY = "5mWqroYoWKPtHbhta4kSetc8hN7euByGmxZgNvIol7EkoS0FQ5Eg43mFHifWFXH3";

	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
		var user = userRepository.findByEmail(request.getUsernameOrEmail())
				.orElseThrow(() -> new RuntimeException("USER NOT FOUND"));
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
		if (!authenticated) {
			throw new RuntimeException("UNAUTHENTICATED");
		}
		var token = genertateToken(request.getUsernameOrEmail());
		return AuthenticationResponseDTO.builder()
				.token(token)
				.authenticated(true)
				.build();
	}

	private String genertateToken(String userNameOrEmail) {
		// build header
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
		// build PayLoad, data trong body goi la claims
		JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
				.subject(userNameOrEmail)
				.issuer("routine.com")
				.issueTime(new Date())
				.expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
				.claim("customClaim", "custom")
				.build();
		Payload payload = new Payload(jwtClaimsSet.toJSONObject());
		// build token va truyen header & payload
		JWSObject jwsObject = new JWSObject(jwsHeader, payload);
		try {
			// build signature token (chu ky)
			jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
			return jwsObject.serialize();
		} catch (JOSEException e) {
			throw new RuntimeException(e);
		}
	}

	public IntrospectResponseDTO introspect(IntrospectRequestDTO requestDTO) {
		var token = requestDTO.getToken();
		try {
			JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
			SignedJWT signedJWT = SignedJWT.parse(token);
			Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
			var verified = signedJWT.verify(verifier);
			return IntrospectResponseDTO.builder()
					.valid(verified && expityTime.after(new Date()))
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

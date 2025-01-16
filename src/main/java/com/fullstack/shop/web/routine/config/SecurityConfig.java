package com.fullstack.shop.web.routine.config;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final String[] PUBLIC_ENDPOINTS_MT_GET = {"/users/all"};
	private final String[] PUBLIC_ENDPOINTS_MT_POST = {"/users/register", "/auth/token", "/auth/introspect"};
	@NonFinal
	private String SIGNER_KEY = "5mWqroYoWKPtHbhta4kSetc8hN7euByGmxZgNvIol7EkoS0FQ5Eg43mFHifWFXH3";

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeHttpRequests(request ->
				request
						.requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS_MT_GET).permitAll()
						.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS_MT_POST).permitAll()
						.anyRequest().authenticated());
		httpSecurity.oauth2ResourceServer(oauth2 ->
				oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		return httpSecurity.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS512");
		return NimbusJwtDecoder
				.withSecretKey(secretKeySpec)
				.macAlgorithm(MacAlgorithm.HS512)
				.build();
	}
}

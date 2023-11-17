package com.dongmanee.global.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dongmanee.global.security.login.entrypoint.CustomAuthenticationEntryPoint;
import com.dongmanee.global.security.login.filter.JwtAuthenticationFilter;
import com.dongmanee.global.security.login.handler.CustomAccessDeniedHandler;
import com.dongmanee.global.security.login.provider.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtProvider jwtProvider;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.httpBasic(AbstractHttpConfigurer::disable) // spring security 기본 인증 해제
			.formLogin(AbstractHttpConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable) // CSRF 공격 방지 기능 해제
			.cors(Customizer.withDefaults()) // CORS 설정

			.headers((headerConfig) ->    // h2-console 표시를 위해
				headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
			)
			.authorizeHttpRequests(authorizedRequests -> { // HTTP 요청에 대한 인가 설정
				authorizedRequests.anyRequest().permitAll(); // 모든 요청에 대해서 인증 없이 허용
			})
			.sessionManagement(
				(session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // session 비활성

			.addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)

			.exceptionHandling((exception) -> {
				exception.accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper));
				exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper));
			});

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

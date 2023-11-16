package com.dongmanee.global.security.login.entrypoint;

import static org.springframework.http.MediaType.*;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.dongmanee.global.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setCharacterEncoding("utf-8");
		response.setContentType(APPLICATION_JSON_VALUE);

		ResponseEntity<?> error = ApiResponse.error(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다.");

		response.getWriter().write(objectMapper.writeValueAsString(error));
	}
}

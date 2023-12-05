package com.dongmanee.domain.security.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dongmanee.domain.security.exception.CustomJwtException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

	/**
	 * JWT 관련 오류를 처리하기 위한 필터
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws ServletException, IOException {
		try {
			chain.doFilter(request, response);
		} catch (CustomJwtException ex) {
			// JwtAuthenticationFilter에서 발생한 예외 처리
			setErrorResponse(request, response, ex);
		}
	}

	private void setErrorResponse(HttpServletRequest req, HttpServletResponse res, CustomJwtException ex)
		throws IOException {

		res.setContentType(MediaType.APPLICATION_JSON_VALUE);

		final Map<String, Object> body = new HashMap<>();
		body.put("status", ex.getHttpStatus().value());
		body.put("error", ex.getMessage());
		body.put("data", null);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(res.getOutputStream(), body);
		res.setStatus(ex.getHttpStatus().value());
	}
}

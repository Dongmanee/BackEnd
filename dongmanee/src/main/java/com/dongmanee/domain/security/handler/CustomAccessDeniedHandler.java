package com.dongmanee.domain.security.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongmanee.domain.security.exception.CustomAccessDeniedException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
		AccessDeniedException accessDeniedException) {
		throw new CustomAccessDeniedException();
	}
}

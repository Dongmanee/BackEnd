package com.dongmanee.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dongmanee.global.error.exception.CustomException;
import com.dongmanee.global.utils.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResponse> handleDuplicateEmailException(CustomException ex) {
		return ApiResponse.error(ex.getHttpStatus(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ApiResponse.error(HttpStatus.BAD_REQUEST,
			ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}
}

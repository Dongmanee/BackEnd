package com.dongmanee.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dongmanee.global.error.exception.CustomException;
import com.dongmanee.global.utils.ApiResult;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ApiResult<?>> handleCustomException(CustomException ex) {
		return ApiResult.error(ex.getHttpStatus(), ex.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResult<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return ApiResult.error(HttpStatus.BAD_REQUEST,
			ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
	}
}

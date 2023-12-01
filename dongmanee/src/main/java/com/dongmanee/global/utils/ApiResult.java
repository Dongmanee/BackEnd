package com.dongmanee.global.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ApiResult<T> {
	private int status;
	private String message;
	private T data;

	public static <T> ApiResult<T> isOk(T data, String message) {
		return new ApiResult<>(HttpStatus.OK.value(), message, data);
	}

	public static ApiResult<?> isCreated(String message) {
		return new ApiResult<>(HttpStatus.CREATED.value(), message, null);
	}

	public static <T> ApiResult<T> isCreated(T data, String message) {
		return new ApiResult<>(HttpStatus.CREATED.value(), message, data);
	}

	public static ApiResult<?> isNoContent(String message) {
		return new ApiResult<>(HttpStatus.NO_CONTENT.value(), message, null);
	}

	public static ResponseEntity<ApiResult<?>> error(HttpStatus errorCode, String message) {
		return ResponseEntity.status(errorCode).body(new ApiResult<>(errorCode.value(), message, null));
	}
}

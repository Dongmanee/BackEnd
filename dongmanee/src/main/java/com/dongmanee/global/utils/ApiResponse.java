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
public class ApiResponse<T> {
	private int status;
	private String message;
	private T data;

	public static ApiResponse<?> isOk(String message) {
		return new ApiResponse<>(HttpStatus.OK.value(), message, null);
	}

	public static <T> ApiResponse<T> isOk(T data, String message) {
		return new ApiResponse<>(HttpStatus.OK.value(), message, data);
	}

	public static ApiResponse<?> isCreated(String message) {
		return new ApiResponse<>(HttpStatus.CREATED.value(), message, null);
	}

	public static ApiResponse<?> isNoContent(String message) {
		return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), message, null);
	}

	public static ResponseEntity<ApiResponse<?>> error(HttpStatus errorCode, String message) {
		return ResponseEntity.status(errorCode).body(new ApiResponse<>(errorCode.value(), message, null));
	}
}

package com.dongmanee.domain.post.exception;

import org.springframework.http.HttpStatus;

import com.dongmanee.global.error.exception.CustomException;

import lombok.Getter;

@Getter
public class ClubPostCategoryNotFoundException extends CustomException {
	private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

	public ClubPostCategoryNotFoundException() {
		super("게시글의 카테고리를 찾을 수 없습니다.");
	}
}

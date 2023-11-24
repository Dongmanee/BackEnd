package com.dongmanee.global.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dongmanee.global.error.exception.GenerateAuthCodeFailedException;

@Component
public class AuthCodeProvider {

	@Value("${auth.code.length}")
	private int authCodeLength;

	public String createAuthCode() {
		try {
			Random random = SecureRandom.getInstanceStrong();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < authCodeLength; i++) {
				builder.append(random.nextInt(10));
			}
			return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new GenerateAuthCodeFailedException("인증 코드 생성 실패");
		}
	}
}

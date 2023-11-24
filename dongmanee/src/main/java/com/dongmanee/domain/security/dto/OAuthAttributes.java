package com.dongmanee.domain.security.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {
		return ofKakao("id", attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		// kakao는 kakao_account에 유저정보가 있다. (email)
		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		Map<String, Object> modifiableAttributes = addExtraAttributes(attributes, kakaoAccount.get("email"));
		// kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
		// Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
		return OAuthAttributes.builder()
			.email((String)kakaoAccount.get("email"))
			.attributes(modifiableAttributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}

	private static Map<String, Object> addExtraAttributes(Map<String, Object> unmodifiableAttributes, Object email) {
		Map<String, Object> modifiableAttributes = new HashMap<>(unmodifiableAttributes);
		modifiableAttributes.put("email", email);

		return modifiableAttributes;
	}
}

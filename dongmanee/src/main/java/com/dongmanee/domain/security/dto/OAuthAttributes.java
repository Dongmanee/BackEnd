package com.dongmanee.domain.security.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String email;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.email = email;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName,
		Map<String, Object> attributes) {
		// if("kakao".equals(registrationId)){
		return ofKakao("id", attributes);
		// }
		// // naver
		// if("naver".equals(registrationId)){
		// 	return ofNaver("id", attributes);
		// }
		// // google
		// return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		// kakao는 kakao_account에 유저정보가 있다. (email)
		Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
		// kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
		// Map<String, Object> kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
		return OAuthAttributes.builder()
			.email((String)kakaoAccount.get("email"))
			.attributes(attributes)
			.nameAttributeKey(userNameAttributeName)
			.build();
	}
}

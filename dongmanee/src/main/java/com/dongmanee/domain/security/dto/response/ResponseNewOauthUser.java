package com.dongmanee.domain.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseNewOauthUser {
	String provider;
	Long externalProviderId;
	String email;
	String code;
}

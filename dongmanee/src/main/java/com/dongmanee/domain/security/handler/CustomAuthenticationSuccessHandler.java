package com.dongmanee.domain.security.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dongmanee.domain.email.utils.EmailRedisUtils;
import com.dongmanee.domain.security.provider.JwtProvider;
import com.dongmanee.global.utils.AuthCodeProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final ObjectMapper objectMapper;
	private final JwtProvider jwtProvider;
	private final AuthCodeProvider authCodeProvider;
	private final EmailRedisUtils emailRedis;

	@Value("${auth.code.expiration-millis}")
	private long authCodeExpirationMillis;

	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException {

		Long id = null;
		String role = null;
		String token = null;

		if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
			// OAuth 로그인 성공 시
			DefaultOAuth2User oAuth2User = (DefaultOAuth2User)oauth2Token.getPrincipal();
			Map<String, Object> attributes = oAuth2User.getAttributes();

			Long memberId = null;

			try {
				memberId = Long.parseLong(String.valueOf(attributes.get("memberId")));
			} catch (NumberFormatException e) {
				// 신규 유저일 시
				String provider = oauth2Token.getAuthorizedClientRegistrationId();
				String email = attributes.get("email").toString();
				newOauthUser(request, response, oAuth2User, provider, email);
				return;
			}

			id = memberId;
			role = oAuth2User.getAuthorities().iterator().next().getAuthority();

			token = jwtProvider.createToken(id, role);

			// 로그인 성공 시 로그인 결과 페이지로 리다이렉트
			String url = "http://localhost:3000/login/result?token=" + token;
			response.setStatus(HttpStatus.PERMANENT_REDIRECT.value());
			response.setHeader("Location", url);
		} else {
			// 로컬 로그인 성공 시
			id = Long.parseLong(authentication.getName());
			role = authentication.getAuthorities().iterator().next().getAuthority();

			token = jwtProvider.createToken(id, role);

			// 로그인 성공 시 토큰 반환
			response.setStatus(HttpStatus.OK.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(token));
		}
	}

	private void newOauthUser(HttpServletRequest request, HttpServletResponse response, OAuth2User oAuth2User,
		String provider, String email) throws IOException {

		Long externalProviderId = Long.valueOf(oAuth2User.getAttributes().get("id").toString());
		String authCode = authCodeProvider.createAuthCode();

		emailRedis.setData(email, authCode, authCodeExpirationMillis);

		String url = "http://localhost:3000/signup?email=" + email + "&code=" + authCode + "&provider=" + provider
			+ "&externalProviderId=" + externalProviderId;
		response.setStatus(HttpStatus.PERMANENT_REDIRECT.value());
		response.setHeader("Location", url);
	}
}

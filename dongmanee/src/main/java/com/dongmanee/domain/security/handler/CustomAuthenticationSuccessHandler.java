package com.dongmanee.domain.security.handler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.security.dao.AuthProviderRepository;
import com.dongmanee.domain.security.dto.response.JwsToken;
import com.dongmanee.domain.security.provider.JwtProvider;
import com.dongmanee.global.utils.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final ObjectMapper objectMapper;
	private final JwtProvider jwtProvider;
	private final AuthProviderRepository authProviderRepository;
	private final MemberRepository memberRepository;

	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException {

		String token = null;

		response.setStatus(HttpStatus.OK.value());
		response.setContentType("application/json;charset=UTF-8");

		if (authentication instanceof OAuth2AuthenticationToken oauth2Token) {
			// OAuth 로그인 성공 시
			OAuth2User oAuth2User = oauth2Token.getPrincipal();

			// 신규 유저일 시
			if (authentication.getAuthorities().stream()
				.anyMatch(authority -> authority.getAuthority().equals(Role.ROLE_GUEST.getKey()))) {
				Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
				String targetUrl = UriComponentsBuilder.fromUriString("http://localhost:8080")
					.queryParam("email", kakaoAccount.get("email"))
					.build()
					.encode(StandardCharsets.UTF_8)
					.toUriString();
				getRedirectStrategy().sendRedirect(request, response, targetUrl);
			}

			Map<String, Object> kakaoAccount = oAuth2User.getAttribute("kakao_account");
			Member member = memberRepository.findByEmail(kakaoAccount.get("email").toString()).orElseThrow();
			Long id = member.getId();
			String role = oAuth2User.getAuthorities().iterator().next().getAuthority();
			token = jwtProvider.createToken(id, role);
		} else {
			// 로컬 로그인 성공 시
			Long id = Long.parseLong(authentication.getName());
			String role = authentication.getAuthorities().iterator().next().getAuthority();
			token = jwtProvider.createToken(id, role);
		}

		// 로그인 성공 시 토큰 반환
		if (token != null) {
			JwsToken jwsToken = JwsToken.of(token);
			response.getWriter().write(objectMapper.writeValueAsString(ApiResponse.success(jwsToken, "로그인 성공")));
		}
	}
}

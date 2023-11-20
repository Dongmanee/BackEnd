package com.dongmanee.domain.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.security.dao.AuthProviderRepository;
import com.dongmanee.domain.security.domain.AuthProvider;
import com.dongmanee.domain.security.dto.OAuthAttributes;
import com.dongmanee.domain.security.exception.UnAuthorizedException;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	private final AuthProviderRepository authProviderRepository;
	private final MemberRepository memberRepository;

	/**
	 * Local Login 인증
	 *
	 * @param username the username identifying the user whose data is required.
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByLoginId(username).orElseThrow(UnAuthorizedException::new);

		// 권한 설정
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(member.getRole().getKey()));

		// User: Spring Security에서 제공해주는 User 모델
		return new User(member.getId().toString(), member.getPassword(), true, true, true, true, authorities);
	}

	/**
	 * OAuth2 Login 인증
	 *
	 * @param userRequest the user request
	 * @return
	 * @throws OAuth2AuthenticationException
	 */
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = defaultOAuth2UserService.loadUser(userRequest);

		// OAuth2 제공자
		String registrationId = userRequest.getClientRegistration().getRegistrationId();

		// 제공자로 부터 유저 정보 가져오기
		String userNameAttributeName = userRequest.getClientRegistration()
			.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();

		// OAuth2UserService
		OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName,
			oAuth2User.getAttributes());

		AuthProvider authProvider = saveOrUpdate(registrationId, attributes);

		return new DefaultOAuth2User(
			Collections.singleton(new SimpleGrantedAuthority(authProvider.getMember().getRole().getKey())),
			attributes.getAttributes(), attributes.getNameAttributeKey());
	}

	private AuthProvider saveOrUpdate(String registrationId, OAuthAttributes attributes) {
		Long externalProviderId = Long.valueOf(attributes.getAttributes().get("id").toString());
		AuthProvider authProvider = authProviderRepository.findByAuthProviderAndExternalProviderId(registrationId,
				externalProviderId)
			// 기존 유저
			.map(provider -> {
				provider.getMember().updateEmail(attributes.getEmail());
				return provider;
			})
			// 신규 유저
			.orElseGet(() -> {
				Member member = new Member();
				member.updateRole(Role.ROLE_GUEST);
				member.updateEmail(attributes.getEmail());
				return AuthProvider.builder()
					.member(memberRepository.save(member))
					.authProvider(registrationId)
					.externalProviderId(externalProviderId)
					.build();
			});

		return authProviderRepository.save(authProvider);
	}
}

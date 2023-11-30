package com.dongmanee.domain.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.service.port.ClubUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClubUserAuthenticationFilter extends OncePerRequestFilter {
	private final ClubUserRepository clubUserRepository;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		// clubId
		String path = request.getRequestURI();
		String[] pathParts = path.split("/");

		OptionalInt clubIndex = IntStream.range(0, pathParts.length)
			.filter(i -> "test".equals(pathParts[i]))
			.findFirst();
		Long clubId = null;

		if (clubIndex.isPresent() && clubIndex.getAsInt() + 1 < pathParts.length) {
			clubId = Long.parseLong(pathParts[clubIndex.getAsInt() + 1]);
		}

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		//userId 및 권한 가져오기
		if (auth != null) {
			long memberId = Long.parseLong(auth.getName());

			Optional<ClubUser> clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId);

			if (clubUser.isPresent()) {

				ClubRole clubRole = clubUser.get().getClubRole();

				// 현재 권한 목록 가져오기
				List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());

				// 새로운 권한 추가
				authorities.add(new SimpleGrantedAuthority("ROLE_"+clubRole.toString()));

				// 새로운 인증 정보 생성
				Authentication newAuth = new UsernamePasswordAuthenticationToken(
					auth.getPrincipal(),
					auth.getCredentials(),
					authorities
				);

				// SecurityContext에 새로운 인증 정보 설정
				context.setAuthentication(newAuth);
			}

		}
		filterChain.doFilter(request, response);
	}
}

package com.dongmanee.domain.security.filter;

import java.io.IOException;
import java.util.ArrayList;
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
import com.dongmanee.domain.club.service.port.ClubServiceClubUserRepository;
import com.dongmanee.domain.security.filter.port.ClubAuthenticationFilterClubUserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClubUserAuthenticationFilter extends OncePerRequestFilter {
	private final ClubAuthenticationFilterClubUserRepository clubUserRepository;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		// uri에서 clubId 가져오기
		Long clubId = getClubIdFromUri(request.getRequestURI());
		// SecurityContext와 인증 객체 꺼내기
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		// 객체가 있을 경우, ClubUser의 Role을 가져오기 시도
		if (auth != null) {
			long memberId = Long.parseLong(auth.getName());

			Optional<ClubUser> clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId);
			// Club의 User가 아닌경우 통과
			if (clubUser.isPresent()) {
				//DB에서 현재 권한 가져오기
				String clubRole = clubUser.get().getClubRole().getKey();

				// 현재 권한 목록 가져오기
				List<GrantedAuthority> authorities = new ArrayList<>(auth.getAuthorities());

				// 새로운 권한 추가
				authorities.add(new SimpleGrantedAuthority(clubRole));

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

	private Long getClubIdFromUri(String path) {
		String[] pathParts = path.split("/");

		OptionalInt clubIndex = IntStream.range(0, pathParts.length)
			.filter(i -> "club".equals(pathParts[i]))
			.findFirst();
		Long clubId = null;

		if (clubIndex.isPresent() && clubIndex.getAsInt() + 1 < pathParts.length) {
			clubId = Long.parseLong(pathParts[clubIndex.getAsInt() + 1]);
		}
		return clubId;
	}
}

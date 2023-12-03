package com.dongmanee.domain.security.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.dongmanee.domain.security.exception.CustomJwtException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {
	private final String salt;
	private final long accessTokenValidityIn;
	private Key secretKey;

	public JwtProvider(@Value("${jwt.secret}") String salt,
		@Value("${jwt.access-token-validity-in-seconds}") Long accessTokenValidityIn) {
		this.salt = salt;
		this.accessTokenValidityIn = accessTokenValidityIn * 1000;
	}

	@PostConstruct
	protected void init() {
		this.secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
	}

	// 토큰 생성
	public String createToken(Long memberId, String roles) {
		Claims claims = Jwts.claims().setSubject(memberId.toString());
		claims.put("roles", roles);
		// claims.put("univId", );
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + accessTokenValidityIn))
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
	}

	public String createToken(Long memberId, String roles, String universityId) {
		Claims claims = Jwts.claims().setSubject(memberId.toString());
		claims.put("roles", roles);
		claims.put("university-id", universityId);
		Date now = new Date();
		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + accessTokenValidityIn))
			.signWith(secretKey, SignatureAlgorithm.HS256)
			.compact();
	}

	// 권한정보 획득
	// Spring Security 인증과정에서 권한확인을 위한 기능
	public Authentication getAuthentication(String token) {
		User user = resolveTokenToUser(token);
		return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
	}

	private User resolveTokenToUser(String token) {
		Claims claim = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		String id = claim.getSubject();
		String role = (String)claim.get("roles");
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));

		return new User(id, "", true, true, true, true, authorities);
	}

	// Authorization Header를 통해 인증을 한다.
	public String resolveToken(HttpServletRequest request) {
		return request.getHeader("Authorization");
	}

	// 토큰 검증
	public boolean validateToken(String token) throws AuthenticationException {
		try {
			token = token.split(" ")[1].trim();
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			log.error("잘못된 JWT 서명입니다.");
			throw new CustomJwtException();
		} catch (ExpiredJwtException e) {
			log.error("만료된 JWT 토큰입니다.");
			throw new CustomJwtException();
		} catch (UnsupportedJwtException e) {
			log.error("지원되지 않는 JWT 토큰입니다.");
			throw new CustomJwtException();
		} catch (IllegalArgumentException e) {
			log.error("JWT 토큰이 잘못되었습니다.");
			throw new CustomJwtException();
		}
	}

}

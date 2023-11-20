package com.dongmanee.domain.security.provider;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dongmanee.domain.security.exception.CustomJwtException;
import com.dongmanee.domain.security.service.AuthService;

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

@Component
public class JwtProvider {
	private final String salt;
	private final long accessTokenValidityIn;
	private final AuthService authService;
	private Key secretKey;

	public JwtProvider(@Value("${jwt.secret}") String salt,
		@Value("${jwt.access-token-validity-in-seconds}") Long accessTokenValidityIn,
		AuthService authService) {
		this.salt = salt;
		this.accessTokenValidityIn = accessTokenValidityIn * 1000;
		this.authService = authService;
	}

	@PostConstruct
	protected void init() {
		this.secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
	}

	// 토큰 생성
	public String createToken(Long memberId, String roles) {
		Claims claims = Jwts.claims().setSubject(memberId.toString());
		claims.put("roles", roles);
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
		UserDetails userDetails = authService.loadUserById(Long.parseLong(this.getAccount(token)));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	// 토큰에 담겨있는 유저 account 획득
	public String getAccount(String token) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
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
			throw new CustomJwtException("잘못된 JWT 서명입니다.", HttpStatus.UNAUTHORIZED); //401
		} catch (ExpiredJwtException e) {
			throw new CustomJwtException("만료된 JWT 토큰입니다.", HttpStatus.UNAUTHORIZED); //401
		} catch (UnsupportedJwtException e) {
			throw new CustomJwtException("지원되지 않는 JWT 토큰입니다.", HttpStatus.BAD_REQUEST); //400
		} catch (IllegalArgumentException e) {
			throw new CustomJwtException("JWT 토큰이 잘못되었습니다.", HttpStatus.UNPROCESSABLE_ENTITY); //422
		}
	}

}

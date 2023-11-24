package com.dongmanee.domain.security.filter;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dongmanee.domain.security.dto.request.SignInRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
		HttpServletResponse response) throws AuthenticationException {
		try {
            /*
                클라이언트에서 전송한 로그인 정보를 읽어옴
                - ObjectMapper: JSON 데이터를 자바 객체로 변환하는 Jackson 라이브러리 클래스
                - request.getInputStream(): HTTP 요청 바디(body)에 포함된 데이터를 읽어오기 위한 메서드
             */
			SignInRequest signInRequest = new ObjectMapper().readValue(request.getInputStream(), SignInRequest.class);

			// getAuthenticationManager: 인증 처리 메서드
			return getAuthenticationManager().authenticate(
                    /*
                        사용자가 입력했던 login_id와 password 값을 spring security에서 사용할 수 있는 형태의 값으로 변환하기 위해서
                        UsernamePasswordAuthenticationToken 형태로 변환
                     */
				new UsernamePasswordAuthenticationToken(
					signInRequest.getEmail(),
					signInRequest.getPassword(),
					// 권한과 관련된 값
					new ArrayList<>()
				)
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

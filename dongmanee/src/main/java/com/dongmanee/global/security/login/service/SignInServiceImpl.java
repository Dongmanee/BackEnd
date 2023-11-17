package com.dongmanee.global.security.login.service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.global.security.login.dto.response.JwsToken;
import com.dongmanee.global.security.login.exception.PasswordUnMatchException;
import com.dongmanee.global.security.login.exception.UnAuthorizedException;
import com.dongmanee.global.security.login.provider.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public JwsToken login(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId)
                .orElseThrow(UnAuthorizedException::new);

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new PasswordUnMatchException();
        }

        String accessToken = jwtProvider.createToken(member.getId(), member.getRole().getKey());

        return JwsToken.of(accessToken);
    }
}

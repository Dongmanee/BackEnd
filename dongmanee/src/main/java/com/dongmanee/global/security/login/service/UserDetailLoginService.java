package com.dongmanee.global.security.login.service;

import com.dongmanee.domain.member.dao.MemberRepository;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.global.security.login.domain.MemberDetails;
import com.dongmanee.global.security.login.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailLoginService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(userId)
                .orElseThrow(UnAuthorizedException::new);

        return MemberDetails.of(member);
    }
}

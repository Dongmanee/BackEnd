package com.dongmanee.global.security.login.domain;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberDetails implements UserDetails {

	private Long id;
	private Role role;
	private String password;

	MemberDetails(Member member) {
		this.id = member.getId();
		this.role = member.getRole();
		this.password = member.getPassword();
	}

	public static MemberDetails of(Member member) {
		return new MemberDetails(member);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.toString());
		Collection<SimpleGrantedAuthority> list = new ArrayList<>();
		list.add(simpleGrantedAuthority);
		return list;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.id.toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

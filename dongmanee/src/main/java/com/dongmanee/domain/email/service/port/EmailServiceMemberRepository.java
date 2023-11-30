package com.dongmanee.domain.email.service.port;

public interface EmailServiceMemberRepository {
	boolean existsByEmail(String email);
}

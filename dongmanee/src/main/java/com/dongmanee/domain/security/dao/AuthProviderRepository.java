package com.dongmanee.domain.security.dao;

import java.util.Optional;

import com.dongmanee.domain.security.dao.jpa.AuthProviderJpaRepository;
import com.dongmanee.domain.security.domain.AuthProvider;

public interface AuthProviderRepository extends AuthProviderJpaRepository {
	Optional<AuthProvider> findByAuthProviderAndExternalProviderId(String authProvider, Long externalProviderId);

	Optional<AuthProvider> findByAuthProviderAndExternalProviderIdWithMemberAndUniversity(String authProvider,
		Long externalProviderId);

	Optional<AuthProvider> findByMemberId(Long memberId);

	boolean existsByMemberId(Long id);
}

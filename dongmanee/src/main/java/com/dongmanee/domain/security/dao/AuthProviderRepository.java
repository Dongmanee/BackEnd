package com.dongmanee.domain.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongmanee.domain.member.service.port.SignUpServiceAuthProviderRepository;
import com.dongmanee.domain.security.domain.AuthProvider;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long>, SignUpServiceAuthProviderRepository {
	Optional<AuthProvider> findByAuthProviderAndExternalProviderId(String authProvider, Long externalProviderId);

	@Query("SELECT a FROM AuthProvider a LEFT JOIN FETCH a.member LEFT JOIN FETCH a.member.university where "
		+ "a.authProvider =:authProvider AND a.externalProviderId =:externalProviderId")
	Optional<AuthProvider> findByAuthProviderAndExternalProviderIdWithMemberAndUniversity(
		@Param(value = "authProvider") String authProvider,
		@Param(value = "externalProviderId") Long externalProviderId);

	Optional<AuthProvider> findByMemberId(Long memberId);

	boolean existsByMemberId(Long id);
}
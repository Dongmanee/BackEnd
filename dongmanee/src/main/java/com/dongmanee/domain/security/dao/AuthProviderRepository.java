package com.dongmanee.domain.security.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.security.domain.AuthProvider;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {
	Optional<AuthProvider> findByAuthProviderAndExternalProviderId(String authProvider, Long externalProviderId);
}
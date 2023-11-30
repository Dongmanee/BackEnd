package com.dongmanee.domain.member.service.port;

import java.util.Optional;

import com.dongmanee.domain.security.domain.AuthProvider;

public interface SignUpServiceAuthProviderRepository {
	Optional<AuthProvider> findByAuthProviderAndExternalProviderId(String provider, Long externalProviderId);
}

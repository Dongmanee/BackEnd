package com.dongmanee.domain.club.service.port;

import java.util.Optional;

import com.dongmanee.domain.club.domain.ClubSns;

public interface ClubSnsRepository {
	ClubSns save(ClubSns clubSns);

	Optional<ClubSns> findById(Long id);

	void delete(ClubSns clubSns);
}

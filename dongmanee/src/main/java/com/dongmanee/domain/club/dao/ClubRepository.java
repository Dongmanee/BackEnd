package com.dongmanee.domain.club.dao;

import java.util.Optional;

import com.dongmanee.domain.club.domain.Club;

public interface ClubRepository {
	Club save(Club club);

	Optional<Club> findById(Long id);
}

package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.service.port.ClubServiceClubRepository;

public interface ClubRepository extends JpaRepository<Club, Long>, ClubServiceClubRepository {
}

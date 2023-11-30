package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.service.port.ClubServiceClubSnsRepository;

public interface ClubSnsRepository extends JpaRepository<ClubSns, Long>, ClubServiceClubSnsRepository {
}

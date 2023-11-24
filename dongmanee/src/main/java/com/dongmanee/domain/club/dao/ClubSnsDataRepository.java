package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.service.port.ClubSnsRepository;

public interface ClubSnsDataRepository extends JpaRepository<ClubSns, Long>, ClubSnsRepository {
}

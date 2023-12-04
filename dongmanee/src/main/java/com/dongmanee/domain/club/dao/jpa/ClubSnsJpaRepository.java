package com.dongmanee.domain.club.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.dao.ClubSnsRepository;
import com.dongmanee.domain.club.domain.ClubSns;

public interface ClubSnsJpaRepository extends JpaRepository<ClubSns, Long>, ClubSnsRepository {
}

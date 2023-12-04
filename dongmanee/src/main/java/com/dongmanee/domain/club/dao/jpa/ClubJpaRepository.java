package com.dongmanee.domain.club.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.dao.ClubRepository;
import com.dongmanee.domain.club.domain.Club;

public interface ClubJpaRepository extends JpaRepository<Club, Long>, ClubRepository {
}

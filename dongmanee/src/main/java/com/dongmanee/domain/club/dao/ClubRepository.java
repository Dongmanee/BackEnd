package com.dongmanee.domain.club.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.domain.Club;

public interface ClubRepository extends JpaRepository<Club, Long> {
}

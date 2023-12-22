package com.dongmanee.domain.club.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dongmanee.domain.club.dao.ClubScheduleRepository;
import com.dongmanee.domain.club.domain.ClubSchedule;

public interface ClubScheduleJpaRepository extends JpaRepository<ClubSchedule, Long>, ClubScheduleRepository {
}

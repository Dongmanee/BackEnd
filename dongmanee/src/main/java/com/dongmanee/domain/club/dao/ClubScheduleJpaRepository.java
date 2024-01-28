package com.dongmanee.domain.club.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dongmanee.domain.club.dao.custom.ClubScheduleCustomRepository;
import com.dongmanee.domain.club.domain.ClubSchedule;

@Repository
public interface ClubScheduleJpaRepository extends JpaRepository<ClubSchedule, Long>, ClubScheduleCustomRepository {
	Optional<ClubSchedule> findByIdAndClubId(long id, long clubId);

	List<ClubSchedule> findByClubIdAndStartTimeBetween(long clubId, LocalDateTime startOfMonth,
		LocalDateTime endOfMonth);

	void deleteByIdAndClubId(long scheduleId, long clubId);
}

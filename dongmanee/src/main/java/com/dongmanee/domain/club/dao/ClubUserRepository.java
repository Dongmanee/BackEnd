package com.dongmanee.domain.club.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongmanee.domain.club.domain.ClubUser;

public interface ClubUserRepository extends JpaRepository<ClubUser, Long> {

	@Query("SELECT cu FROM ClubUser cu LEFT JOIN FETCH cu.member LEFT JOIN FETCH cu.club "
		+ "WHERE cu.member.id = :memberId AND cu.club.id =:clubId")
	Optional<ClubUser> findClubUserWithMemberClub(@Param("memberId") Long memberId, @Param("clubId") Long clubId);

	@Query("SELECT cu FROM ClubUser cu LEFT JOIN FETCH cu.member LEFT JOIN FETCH cu.club "
		+ "WHERE cu.member.id = :memberId AND cu.club.id =:clubId")
	Optional<ClubUser> findClubUserWithMemberClub(@Param("memberId") Long memberId, @Param("clubId") Long clubId);

}

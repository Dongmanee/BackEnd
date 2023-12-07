package com.dongmanee.domain.club.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongmanee.domain.club.domain.ClubUser;

public interface ClubUserRepository {
	ClubUser save(ClubUser clubUser);

	Optional<ClubUser> findClubUserWithMemberClub(Long memberId, Long clubId);

	@Query("SELECT cu FROM ClubUser cu LEFT JOIN FETCH cu.member LEFT JOIN FETCH cu.club "
		+ "WHERE cu.member.id =:memberId")
	List<ClubUser> findWithMember(@Param("memberId") Long memberId);
}

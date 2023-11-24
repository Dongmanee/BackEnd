package com.dongmanee.domain.club.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongmanee.domain.club.dao.ClubRepository;
import com.dongmanee.domain.club.dao.ClubSnsRepository;
import com.dongmanee.domain.club.dao.ClubUserRepository;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.exception.ClubUserNotFoundException;
import com.dongmanee.domain.club.exception.IllegalAccessException;
import com.dongmanee.domain.club.exception.SnsNotFoundException;
import com.dongmanee.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService, ClubInfoUpdateService {

	private final ClubRepository clubRepository;
	private final ClubUserRepository clubUserRepository;
	private final ClubSnsRepository clubSnsRepository;

	@Override
	@Transactional
	public void createClub(Club club, Member member) {
		Club newClub = makeClub(club, member);
		clubRepository.save(newClub);
		ClubUser hostPermission = createUserWithHostPermission(newClub, member);
		clubUserRepository.save(hostPermission);
	}

	@Override
	public void editClubDescriptionAndAddress(Long memberId, Club club) {
		// 유저 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, club.getId())
			.orElseThrow(ClubUserNotFoundException::new);
		// 권한 확인
		checkIsManager(clubUser);
		// 수정
		Club targetClub = clubUser.getClub();
		targetClub.editDescriptionAndAddress(club);
		clubRepository.save(targetClub);
	}

	@Override
	public void addClubSns(Long memberId, ClubSns clubSns, Long clubId) {
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 유저의 권한 확인
		checkIsManager(clubUser);
		// 추가
		clubSns.addClub(clubUser.getClub());
		clubSnsRepository.save(clubSns);
	}

	// TODO: editClubSns, removeClubSns 추후 한번에 쿼리로 fetch join 하는 방식과 시간 비교 필요
	@Override
	public void editClubSns(Long memberId, ClubSns clubSns, Long clubId, Long snsId) {
		//타켓 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 유저의 권한 확인
		checkIsManager(clubUser);
		// 목표 엔티티 검색
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(SnsNotFoundException::new);
		// 수정
		targetSns.editClubSns(clubSns);
		clubSnsRepository.save(targetSns);
	}

	@Override
	public void removeClubSns(Long memberId, Long clubId, Long snsId) {
		// 타켓 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 유저 권한 확인
		checkIsManager(clubUser);
		// 삭제
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(SnsNotFoundException::new);

		clubSnsRepository.delete(targetSns);
	}

	private void checkIsManager(ClubUser clubUser) {
		if (clubUser.getClubRole().equals(ClubRole.USER)) {
			throw new IllegalAccessException();
		}
	}

	private Club makeClub(Club club, Member member) {
		return Club.builder()
			.university(member.getUniversity())
			.clubUsers(new ArrayList<>())
			.category(club.getCategory())
			.clubSns(new ArrayList<>())
			.name(club.getName())
			.description(club.getDescription())
			.clubMainImageUrl(club.getClubMainImageUrl())
			.clubBackgroundImageUrl(club.getClubBackgroundImageUrl())
			.address(club.getAddress())
			.isDeleted(false)
			.build();
	}

	private ClubUser createUserWithHostPermission(Club club, Member hostMember) {
		return ClubUser.builder()
			.member(hostMember)
			.club(club)
			.clubRole(ClubRole.HOST)
			.build();
	}
}

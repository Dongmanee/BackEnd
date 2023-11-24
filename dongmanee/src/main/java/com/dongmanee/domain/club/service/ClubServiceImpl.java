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
import com.dongmanee.domain.club.exception.ClubNotExistException;
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
	public void editClubDescriptionAndAddress(Club club, Member member) {
		// 유저 조회, 권한 확인
		ClubUser clubUser = checkIsHost(member, club);
		// 수정
		Club targetClub = clubUser.getClub();
		targetClub.editDescriptionAndAddress(club);
	}

	@Override
	public void addClubSns(ClubSns clubSns, Long clubId, Member member) {
		// 유저의 권한 확인
		Club targetClub = clubRepository.findById(clubId).orElseThrow(ClubNotExistException::new);
		checkIsManager(member, targetClub);
		// 추가
		clubSns.addClub(targetClub);
		clubSnsRepository.save(clubSns);
	}

	@Override
	public void editClubSns(ClubSns clubSns, Member member, Long clubId, Long snsId) {
		// 유저의 권한 확인
		Club targetClub = clubRepository.findById(clubId).orElseThrow(ClubNotExistException::new);
		checkIsManager(member, targetClub);
		// 목표 엔티티 검색
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(SnsNotFoundException::new);
		// 수정
		targetSns.editClubSns(clubSns);
	}

	@Override
	public void removeClubSns(Member member, Long clubId, Long snsId) {
		//유저의 권한 확인

		//삭제
		Club targetClub = clubRepository.findById(clubId).orElseThrow(ClubNotExistException::new);
		checkIsManager(member, targetClub);
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(SnsNotFoundException::new);

		clubSnsRepository.delete(targetSns);
	}

	private void checkIsManager(Member member, Club club) {
		ClubUser clubUser = clubUserRepository.find(member.getId(), club.getId())
			.orElseThrow(ClubUserNotFoundException::new);
		if (clubUser.getClubRole().equals(ClubRole.USER)) {
			throw new IllegalAccessException();
		}
	}

	private ClubUser checkIsHost(Member member, Club club) {
		ClubUser clubUser = clubUserRepository.find(member.getId(), club.getId())
			.orElseThrow(ClubUserNotFoundException::new);
		if (!clubUser.getClubRole().equals(ClubRole.HOST)) {
			throw new IllegalAccessException();
		}
		return clubUser;
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

package com.dongmanee.domain.club.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongmanee.domain.club.controller.port.ClubInfoUpdateService;
import com.dongmanee.domain.club.controller.port.ClubService;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.exception.ClubUserNotFoundException;
import com.dongmanee.domain.club.exception.ClubSnsNotFoundException;
import com.dongmanee.domain.club.service.port.ClubServiceClubRepository;
import com.dongmanee.domain.club.service.port.ClubServiceClubSnsRepository;
import com.dongmanee.domain.club.service.port.ClubServiceClubUserRepository;
import com.dongmanee.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService, ClubInfoUpdateService {

	private final ClubServiceClubRepository clubRepository;
	private final ClubServiceClubUserRepository clubUserRepository;
	private final ClubServiceClubSnsRepository clubSnsRepository;

	@Override
	@Transactional
	public void createClub(Club club, Member member) {
		Club newClub = makeClub(club, member);
		clubRepository.save(newClub);
		ClubUser hostUser = createUserWithHostPermission(newClub, member);
		clubUserRepository.save(hostUser);
	}

	@Override
	public Club editClubDescriptionAndAddress(Long memberId, Club club) {
		// 유저 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, club.getId())
			.orElseThrow(ClubUserNotFoundException::new);
		// 수정
		Club targetClub = clubUser.getClub();
		targetClub.editClub(club);
		return targetClub;
	}

	@Override
	public ClubSns addClubSns(Long memberId, ClubSns clubSns, Long clubId) {
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 추가
		clubSns.addClub(clubUser.getClub());
		return clubSnsRepository.save(clubSns);
	}

	// TODO: editClubSns, removeClubSns 추후 한번에 쿼리로 fetch join 하는 방식과 시간 비교 필요
	@Override
	public ClubSns editClubSns(Long memberId, ClubSns clubSns, Long clubId, Long snsId) {
		//타켓 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 목표 엔티티 검색
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(ClubSnsNotFoundException::new);
		// 수정
		targetSns.editClubSns(clubSns);

		return targetSns;
	}

	@Override
	public void removeClubSns(Long memberId, Long clubId, Long snsId) {
		// 타켓 조회
		ClubUser clubUser = clubUserRepository.findClubUserWithMemberClub(memberId, clubId)
			.orElseThrow(ClubUserNotFoundException::new);
		// 삭제
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(ClubSnsNotFoundException::new);

		clubSnsRepository.delete(targetSns);
	}

	//TODO Mapper로 추후 변경
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

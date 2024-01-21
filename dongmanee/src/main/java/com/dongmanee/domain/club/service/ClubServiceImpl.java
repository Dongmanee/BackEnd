package com.dongmanee.domain.club.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongmanee.domain.club.dao.ClubCategoryRepository;
import com.dongmanee.domain.club.dao.ClubRepository;
import com.dongmanee.domain.club.dao.ClubSnsRepository;
import com.dongmanee.domain.club.dao.ClubUserRepository;
import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.exception.CategoryNotFoundException;
import com.dongmanee.domain.club.exception.ClubSnsNotFoundException;
import com.dongmanee.domain.club.exception.ClubUserNotFoundException;
import com.dongmanee.domain.club.exception.DuplicatedClubSnsException;
import com.dongmanee.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

	private final ClubRepository clubRepository;
	private final ClubUserRepository clubUserRepository;
	private final ClubSnsRepository clubSnsRepository;
	private final ClubCategoryRepository clubCategoryRepository;

	@Override
	@Transactional
	public void createClub(Club club, Member member, List<ClubSns> clubSnsList) {
		Club newClub = makeClub(club, member);
		clubRepository.save(newClub);

		HashSet<Object> isSnsSaved = new HashSet<>();

		Optional.ofNullable(clubSnsList)
			.orElseGet(Collections::emptyList) // clubSnsList가 null일 때의 대처 빈 리스트 반환
			.forEach(clubSns -> {
				if (isSnsSaved.contains(clubSns.getTitle())) {
					throw new DuplicatedClubSnsException();
				}
				isSnsSaved.add(clubSns.getTitle());

				clubSns.addClub(newClub);
				clubSnsRepository.save(clubSns);
			});

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

	@Override
	public List<Club> getJoinedClubList(Long memberId) {
		List<ClubUser> clubUsers = clubUserRepository.findWithMember(memberId);
		return clubUsers.stream().map(ClubUser::getClub).toList();
	}

	// TODO: editClubSns, removeClubSns 추후 한번에 쿼리로 fetch join 하는 방식과 시간 비교 필요
	@Override
	public ClubSns editClubSns(ClubSns clubSns, Long clubId, Long snsId) {
		// 목표 엔티티 검색
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(ClubSnsNotFoundException::new);
		// 수정
		targetSns.editClubSns(clubSns);

		return targetSns;
	}

	@Override
	public void removeClubSns(Long clubId, Long snsId) {
		// 삭제
		ClubSns targetSns = clubSnsRepository.findById(snsId).orElseThrow(ClubSnsNotFoundException::new);

		clubSnsRepository.delete(targetSns);
	}

	@Override
	public ClubCategory findById(Long categoryId) {
		return clubCategoryRepository.findById(categoryId).orElseThrow(CategoryNotFoundException::new);
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

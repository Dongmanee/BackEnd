package com.dongmanee.small.club.service;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.service.ClubServiceImpl;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.small.club.testdoubles.fake.FakeClubRepository;
import com.dongmanee.small.club.testdoubles.fake.FakeClubSnsRepository;
import com.dongmanee.small.club.testdoubles.fake.FakeClubUserRepository;

public class ClubServiceTest {
	private ClubServiceImpl clubService;

	@BeforeEach
	public void init() {
		University testUniversity = University.builder()
			.id(1L)
			.name("testUniv")
			.build();

		ClubCategory testCategory = ClubCategory.builder()
			.id(1L)
			.name("testCategory")
			.build();

		Member testMember = Member.builder()
			.id(1L)
			.clubUsers(new ArrayList<>())
			.university(testUniversity)
			.role(Role.ROLE_USER)
			.studentId("12345678")
			.department("testDepartment")
			.name("tester")
			.phone("010-1234-5678")
			.email("test@gmail.com")
			.birth(LocalDate.of(1999, 1, 1))
			.password("testPassword1!")
			.build();

		Club testClub1 = Club.builder()
			.university(testUniversity)
			.clubUsers(new ArrayList<>())
			.category(testCategory)
			.clubSns(new ArrayList<>())
			.name("TestClub1")
			.isDeleted(false)
			.build();

		ClubUser testClubUser1 = ClubUser.builder()
			.member(testMember)
			.club(testClub1)
			.clubRole(ClubRole.HOST)
			.build();

		ClubSns testClubSns1 = ClubSns.builder()
			.club(testClub1)
			.title("testSns")
			.url("testUrl")
			.build();

		testClub1.appendClubUser(testClubUser1);
		testClub1.appendClubSns(testClubSns1);

		FakeClubRepository fakeClubRepository = new FakeClubRepository();
		fakeClubRepository.save(testClub1);

		FakeClubUserRepository fakeClubUserRepository = new FakeClubUserRepository();
		fakeClubUserRepository.save(testClubUser1);

		FakeClubSnsRepository fakeClubSnsRepository = new FakeClubSnsRepository();
		fakeClubSnsRepository.save(testClubSns1);

		clubService = new ClubServiceImpl(
			fakeClubRepository,
			fakeClubUserRepository,
			fakeClubSnsRepository
		);
	}

}

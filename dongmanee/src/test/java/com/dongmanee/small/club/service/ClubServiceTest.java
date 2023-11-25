package com.dongmanee.small.club.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.club.domain.ClubCategory;
import com.dongmanee.domain.club.domain.ClubSns;
import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.club.enums.ClubRole;
import com.dongmanee.domain.club.exception.IllegalAccessException;
import com.dongmanee.domain.club.service.ClubServiceImpl;
import com.dongmanee.domain.member.domain.Member;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.small.club.testdoubles.fake.FakeClubRepository;
import com.dongmanee.small.club.testdoubles.fake.FakeClubSnsRepository;
import com.dongmanee.small.club.testdoubles.fake.FakeClubUserRepository;

public class ClubServiceTest {
	private ClubServiceImpl clubService;

	private University testUniversity;
	private ClubCategory testCategory;
	private Member testHostMember;
	private Member testAdminMember;
	private Member testNormalMember;
	private Club testClub;
	private ClubSns testSns;

	private FakeClubRepository fakeClubRepository;
	private FakeClubUserRepository fakeClubUserRepository;
	private FakeClubSnsRepository fakeClubSnsRepository;

	@BeforeEach
	public void init() {
		testUniversity = University.builder()
			.id(1L)
			.name("testUniv")
			.build();

		testCategory = ClubCategory.builder()
			.id(1L)
			.name("testCategory")
			.build();

		testHostMember = Member.builder()
			.id(1L)
			.clubUsers(new ArrayList<>())
			.university(testUniversity)
			.role(Role.ROLE_USER)
			.studentId("12345678")
			.department("testDepartment")
			.name("hostTester")
			.phone("010-1234-5678")
			.email("test@gmail.com")
			.birth(LocalDate.of(1999, 1, 1))
			.password("testPassword1!")
			.build();

		testAdminMember = Member.builder()
			.id(2L)
			.clubUsers(new ArrayList<>())
			.university(testUniversity)
			.role(Role.ROLE_USER)
			.studentId("12345678")
			.department("testDepartment")
			.name("adminTester")
			.phone("010-1234-5678")
			.email("test@gmail.com")
			.birth(LocalDate.of(1999, 1, 1))
			.password("testPassword1!")
			.build();

		testNormalMember = Member.builder()
			.id(3L)
			.clubUsers(new ArrayList<>())
			.university(testUniversity)
			.role(Role.ROLE_USER)
			.studentId("12345678")
			.department("testDepartment")
			.name("adminTester")
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

		ClubUser testHostClubUser1 = ClubUser.builder()
			.member(testHostMember)
			.club(testClub1)
			.clubRole(ClubRole.HOST)
			.build();

		ClubUser testAdminClubUser1 = ClubUser.builder()
			.member(testAdminMember)
			.club(testClub1)
			.clubRole(ClubRole.ADMIN)
			.build();

		ClubUser testNormalClubUser1 = ClubUser.builder()
			.member(testNormalMember)
			.club(testClub1)
			.clubRole(ClubRole.USER)
			.build();

		ClubSns testClubSns1 = ClubSns.builder()
			.club(testClub1)
			.title("testSns")
			.url("testUrl")
			.build();

		testClub1.appendClubUser(testHostClubUser1);
		testClub1.appendClubUser(testAdminClubUser1);
		testClub1.appendClubUser(testNormalClubUser1);

		testClub1.appendClubSns(testClubSns1);

		fakeClubRepository = new FakeClubRepository();
		testClub = fakeClubRepository.save(testClub1);

		fakeClubUserRepository = new FakeClubUserRepository();
		fakeClubUserRepository.save(testHostClubUser1);
		fakeClubUserRepository.save(testAdminClubUser1);
		fakeClubUserRepository.save(testNormalClubUser1);

		fakeClubSnsRepository = new FakeClubSnsRepository();
		testSns = fakeClubSnsRepository.save(testClubSns1);

		clubService = new ClubServiceImpl(
			fakeClubRepository,
			fakeClubUserRepository,
			fakeClubSnsRepository
		);
	}

	@DisplayName("클럽 생성")
	@Nested
	class CreateClub {
		@DisplayName("성공 케이스")
		@Test
		void successCase() {
			//given
			Club newClub = Club.builder()
				.university(testUniversity)
				.clubUsers(new ArrayList<>())
				.category(testCategory)
				.clubSns(new ArrayList<>())
				.name("TestClub2")
				.isDeleted(false)
				.build();

			//when
			clubService.createClub(newClub, testNormalMember);
			//then
			Club findClub = fakeClubRepository.getLastClub().get();
			ClubUser clubUser = fakeClubUserRepository
				.findClubUserWithMemberClub(testNormalMember.getId(), findClub.getId())
				.get();

			assertThat(clubUser.getClubRole()).isEqualTo(ClubRole.HOST);
		}
	}

	@DisplayName("클럽 정보 수정")
	@Nested
	class EditClub {
		@DisplayName("클럽의 설명, 주소 수정")
		@Nested
		class EditClubDescriptionAndAddress {
			@DisplayName("성공 케이스")
			@Nested
			class SuccessCase {
				@DisplayName("Host가 변경시")
				@Test
				void editByHost() {
					//given
					Club editData = Club.builder()
						.id(testClub.getId())
						.description("testDescription")
						.address("testAddress")
						.build();
					//when
					clubService.editClubDescriptionAndAddress(testHostMember.getId(), editData);
					//then
					Club club = fakeClubRepository.getLastClub().get();
					assertThat(club.getDescription()).isEqualTo(editData.getDescription());
					assertThat(club.getAddress()).isEqualTo(editData.getAddress());
				}

				@DisplayName("Admin이 변경시")
				@Test
				void editByAdmin() {
					//given
					Club editData = Club.builder()
						.id(testClub.getId())
						.description("testDescription")
						.address("testAddress")
						.build();
					//when
					clubService.editClubDescriptionAndAddress(testAdminMember.getId(), editData);
					//then
					Club club = fakeClubRepository.getLastClub().get();
					assertThat(club.getDescription()).isEqualTo(editData.getDescription());
					assertThat(club.getAddress()).isEqualTo(editData.getAddress());
				}
			}

			@DisplayName("실패 케이스")
			@Nested
			class FailureCase {
				@DisplayName("일반 유저가 변경시")
				@Test
				void editByNormalUser() {
					//given
					Club editData = Club.builder()
						.id(testClub.getId())
						.description("testDescription")
						.address("testAddress")
						.build();
					//when
					//then
					assertThatThrownBy(() -> {
						clubService.editClubDescriptionAndAddress(testNormalMember.getId(), editData);
					}).isInstanceOf(IllegalAccessException.class);
				}
			}
		}

		@DisplayName("클럽 SNS")
		@Nested
		class ClubSnsData {

			@DisplayName("클럽 추가")
			@Nested
			class AddClubSns {
				@DisplayName("성공 케이스")
				@Nested
				class SuccessCase {
					@DisplayName("Host가 변경시")
					@Test
					void byHost() {
						//given
						Long hostMemberId = testHostMember.getId();
						Long targetClubId = testClub.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						clubService.addClubSns(hostMemberId, editData, targetClubId);
						//then
						ClubSns targetSns = fakeClubSnsRepository.getLastClubSns().get();
						assertThat(editData.getTitle()).isEqualTo(targetSns.getTitle());
						assertThat(editData.getUrl()).isEqualTo(targetSns.getUrl());
					}

					@DisplayName("Admin이 변경시")
					@Test
					void byAdmin() {
						//given
						Long adminMemberId = testAdminMember.getId();
						Long targetClubId = testClub.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						clubService.addClubSns(adminMemberId, editData, targetClubId);
						//then
						ClubSns targetSns = fakeClubSnsRepository.getLastClubSns().get();
						assertThat(editData.getTitle()).isEqualTo(targetSns.getTitle());
						assertThat(editData.getUrl()).isEqualTo(targetSns.getUrl());
					}
				}

				@DisplayName("실패 케이스")
				@Nested
				class FailureCase {
					@DisplayName("일반 유저가 변경시")
					@Test
					void byNormalUser() {
						//given
						Long normalMemberId = testNormalMember.getId();
						Long targetClubId = testClub.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						//then
						assertThatThrownBy(() -> {
							clubService.addClubSns(normalMemberId, editData, targetClubId);
						}).isInstanceOf(IllegalAccessException.class);
					}
				}
			}

			@DisplayName("클럽 sns 수정")
			@Nested
			class EditClubSns {
				@DisplayName("성공 케이스")
				@Nested
				class SuccessCase {
					@DisplayName("Host가 변경시")
					@Test
					void byHost() {
						//given
						Long hostMemberId = testHostMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						clubService.editClubSns(hostMemberId, editData, targetClubId, targetSnsId);
						//then
						ClubSns clubSns = fakeClubSnsRepository.findById(targetSnsId).get();
						assertThat(clubSns.getTitle()).isEqualTo(editData.getTitle());
						assertThat(clubSns.getUrl()).isEqualTo(editData.getUrl());
					}

					@DisplayName("Admin이 변경시")
					@Test
					void byAdmin() {
						//given
						Long adminMemberId = testAdminMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						clubService.editClubSns(adminMemberId, editData, targetClubId, targetSnsId);
						//then
						ClubSns clubSns = fakeClubSnsRepository.findById(targetSnsId).get();
						assertThat(clubSns.getTitle()).isEqualTo(editData.getTitle());
						assertThat(clubSns.getUrl()).isEqualTo(editData.getUrl());
					}
				}

				@DisplayName("실패 케이스")
				@Nested
				class FailureCase {
					@DisplayName("일반 유저가 변경시")
					@Test
					void byNormalUser() {
						//given
						Long normalMemberId = testNormalMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						ClubSns editData = ClubSns.builder()
							.title("newClubSnsTitle")
							.url("newClubSnsUrl")
							.build();
						//when
						//then
						assertThatThrownBy(() -> {
							clubService.editClubSns(normalMemberId, editData, targetClubId, targetSnsId);
						}).isInstanceOf(IllegalAccessException.class);
					}
				}
			}

			@DisplayName("클럽 삭제")
			@Nested
			class RemoveClubSns {
				@DisplayName("성공 케이스")
				@Nested
				class SuccessCase {
					@DisplayName("Host가 변경시")
					@Test
					void byHost() {
						//given
						Long hostMemberId = testHostMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						//when
						clubService.removeClubSns(hostMemberId, targetClubId, targetSnsId);
						//then
						Optional<ClubSns> byId = fakeClubSnsRepository.findById(targetSnsId);
						assertThat(byId.isEmpty()).isTrue();
					}

					@DisplayName("Admin이 변경시")
					@Test
					void byAdmin() {
						//given
						Long adminMemberId = testAdminMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						//when
						clubService.removeClubSns(adminMemberId, targetClubId, targetSnsId);
						//then
						Optional<ClubSns> byId = fakeClubSnsRepository.findById(targetSnsId);
						assertThat(byId.isEmpty()).isTrue();
					}
				}

				@DisplayName("실패 케이스")
				@Nested
				class FailureCase {
					@DisplayName("일반 유저가 변경시")
					@Test
					void byNormalUser() {
						//given
						Long normalMemberId = testNormalMember.getId();
						Long targetClubId = testClub.getId();
						Long targetSnsId = testSns.getId();
						//when
						//then
						assertThatThrownBy(() -> {
							clubService.removeClubSns(normalMemberId, targetClubId, targetSnsId);
						}).isInstanceOf(IllegalAccessException.class);
					}
				}
			}

		}
	}

}

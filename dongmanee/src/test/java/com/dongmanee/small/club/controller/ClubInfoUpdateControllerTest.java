package com.dongmanee.small.club.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.club.controller.ClubInfoUpdateController;
import com.dongmanee.domain.club.dto.request.RequestEditClubDescriptionAndAddress;
import com.dongmanee.domain.club.dto.request.RequestSns;
import com.dongmanee.global.utils.ApiResponse;
import com.dongmanee.small.club.testdoubles.fake.ClubMapperFake;
import com.dongmanee.small.club.testdoubles.fake.ClubSnsMapperFake;
import com.dongmanee.small.club.testdoubles.stub.ClubInfoUpdateServiceStub;

public class ClubInfoUpdateControllerTest {
	private final ClubInfoUpdateController clubInfoUpdateController
		= new ClubInfoUpdateController(new ClubMapperFake(), new ClubInfoUpdateServiceStub(), new ClubSnsMapperFake());
	private final UserDetails user = User.builder()
		.username("1")
		.password("passwd")
		.authorities(new ArrayList<>())
		.accountExpired(false)
		.accountLocked(false)
		.build();

	@DisplayName("클럽 설명과 주소 변경")
	@Nested
	class EditClubDescriptionAndAddress {
		@DisplayName("성공 케이스")
		@Test
		void successCase() {
			//given
			RequestEditClubDescriptionAndAddress requestData = RequestEditClubDescriptionAndAddress.builder()
				.build();
			Long clubId = 1L;
			//when
			ApiResponse<?> response = clubInfoUpdateController
				.editClubDescriptionAndAddress(requestData, user, clubId);
			//then
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			assertThat(response.getMessage()).isEqualTo("클럽 정보가 수정되었습니다.");
		}
	}

	@DisplayName("클럽 Sns 정보")
	@Nested
	class ClubSnsInfo {
		@DisplayName("데이터 추가")
		@Test
		void addData() {
			//given
			RequestSns requestData = RequestSns.builder().build();
			Long clubId = 1L;
			//when
			ApiResponse<?> response = clubInfoUpdateController.addClubSns(requestData, user, clubId);
			//then
			assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
			assertThat(response.getMessage()).isEqualTo("클럽 Sns가 추가되었습니다");
		}

		@DisplayName("데이터 수정")
		@Test
		void editData() {
			//given
			RequestSns requestData = RequestSns.builder().build();
			Long clubId = 1L;
			Long clubSnsId = 1L;
			//when
			ApiResponse<?> response = clubInfoUpdateController.editClubSns(requestData, user, clubId, clubSnsId);
			//then
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			assertThat(response.getMessage()).isEqualTo("클럽 Sns가 수정되었습니다");
		}

		@DisplayName("데이터 삭제")
		@Test
		void removeData() {
			//given
			Long clubId = 1L;
			Long clubSnsId = 1L;
			//when
			ApiResponse<?> response = clubInfoUpdateController.removeClubSns(user, clubId, clubSnsId);
			//then
			assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
			assertThat(response.getMessage()).isEqualTo("클럽 Sns가 삭제되었습니다");
		}

	}
}

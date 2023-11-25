package com.dongmanee.small.club.controller;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.club.controller.ClubController;
import com.dongmanee.domain.club.dto.request.RequestCreateClub;
import com.dongmanee.global.utils.ApiResponse;
import com.dongmanee.small.club.testdoubles.fake.ClubMapperFake;
import com.dongmanee.small.club.testdoubles.stub.ClubServiceStub;
import com.dongmanee.small.club.testdoubles.stub.MemberServiceStub;

public class ClubControllerTest {
	private final ClubController clubController
		= new ClubController(new ClubMapperFake(), new ClubServiceStub(), new MemberServiceStub());

	private final UserDetails user = User.builder()
		.username("1")
		.password("passwd")
		.authorities(new ArrayList<>())
		.accountExpired(false)
		.accountLocked(false)
		.build();

	@DisplayName("클럽 생성")
	@Nested
	class CreateClub {
		@DisplayName("성공 케이스")
		@Test
		void successCase() {
			//given
			RequestCreateClub requestData = RequestCreateClub.builder()
				.build();
			//when
			ApiResponse<?> response = clubController.createClub(requestData, user);
			//then
			assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
			assertThat(response.getMessage()).isEqualTo("클럽이 생성되었습니다.");
		}

	}
}

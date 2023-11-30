package com.dongmanee.domain.member.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dongmanee.domain.member.controller.mapper.MemberMapper;
import com.dongmanee.domain.member.dto.request.RequestSignup;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(MemberController.class)
class MemberControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean(JpaMetamodelMappingContext.class)
	JpaMetamodelMappingContext jpaMetamodelMappingContext;
	@MockBean
	MemberController memberController;
	@MockBean
	MemberMapper memberMapper;

	@Test
	@WithMockUser
	public void testMemberSignUpSuccess() throws Exception {
		// Given
		RequestSignup requestSignupDto1 = RequestSignup
			.builder()
			.universityId(1L)
			.studentId("20221415")
			.department("컴퓨터공학과")
			.name("홍길동")
			.phone("01012345678")
			.email("test@test.com")
			.birth(LocalDate.of(1999, 01, 01))
			.loginId("test1234")
			.password("@@test1234")
			.build();

		// When
		ResultActions result1 = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(requestSignupDto1)));

		// Then
		result1.andExpect(status().isOk())
			.andExpect(jsonPath("$.status").value(200))
			.andExpect(jsonPath("$.message").value("회원가입 성공"))
			.andExpect(jsonPath("$.data").doesNotExist());
	}

	@Test
	@WithMockUser
	public void testMemberSignUpFail() throws Exception {
		// Given
		RequestSignup requestDto1 = new RequestSignup(); // 적절한 데이터로 초기화
		requestDto1.setUniversityId(1L);
		requestDto1.setStudentId("20221415");
		requestDto1.setDepartment("컴퓨터공학과");
		requestDto1.setName("홍길동");
		requestDto1.setPhone("01012345678");
		requestDto1.setEmail("test@test.com");
		requestDto1.setBirth(LocalDate.of(1999, 01, 01));
		requestDto1.setLoginId("test1234");
		requestDto1.setPassword("@@Test1234");

		RequestSignup requestDto2 = new RequestSignup(); // 적절한 데이터로 초기화
		requestDto1.setUniversityId(1L);
		requestDto1.setStudentId("20221415");
		requestDto1.setDepartment("컴퓨터공학과");
		requestDto1.setName("홍길동");
		requestDto1.setPhone("01012345679");
		requestDto1.setEmail("test@example.com");
		requestDto1.setBirth(LocalDate.of(1999, 01, 01));
		requestDto1.setLoginId("test1235");
		requestDto1.setPassword("@@Test1234");

		// When
		ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
			.with(SecurityMockMvcRequestPostProcessors.csrf())
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(requestDto1)));

		// Then
		response.andExpect(status().isOk());
	}

	// Helper 메서드: 객체를 JSON 문자열로 변환
	private static String asJsonString(final Object obj) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			return objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
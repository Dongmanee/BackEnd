package com.dongmanee.medium.member.service;

// @SpringBootTest
// class MemberServiceSignUpTest {
// 	@Autowired
// 	MemberServiceImpl memberService;
// 	@Autowired
// 	MemberRepository memberRepository;
//
// 	@AfterEach
// 	public void afterEach() {
// 		memberRepository.deleteAll();
// 	}
//
// 	@Test
// 	public void MemberSaveTest() {
// 		// Given
// 		Member newMember1 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221415")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345678")
// 			.email("test@test.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test1234")
// 			.password("@@test1234")
// 			.build();
//
// 		Member newMember2 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221416")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345679")
// 			.email("test@example.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test1233")
// 			.password("@@test1234")
// 			.build();
//
// 		// When
// 		memberService.signup(newMember1);
// 		memberService.signup(newMember2);
//
// 		// Then
// 		Member savedMember1 = memberRepository.findByLoginId(newMember1.getLoginId());
// 		Member savedMember2 = memberRepository.findByLoginId(newMember2.getLoginId());
//
// 		assertThat(savedMember1.getId()).isNotEqualTo(savedMember2.getId());
//
// 		assertThat(savedMember1.getUniversityId()).isEqualTo(newMember1.getUniversityId());
// 		assertThat(savedMember1.getStudentId()).isEqualTo(newMember1.getStudentId());
// 		assertThat(savedMember1.getDepartment()).isEqualTo(newMember1.getDepartment());
// 		assertThat(savedMember1.getName()).isEqualTo(newMember1.getName());
// 		assertThat(savedMember1.getPhone()).isEqualTo(newMember1.getPhone());
// 		assertThat(savedMember1.getLoginId()).isEqualTo(newMember1.getLoginId());
// 		assertThat(savedMember1.getEmail()).isEqualTo(newMember1.getEmail());
// 		assertThat(savedMember1.getBirth()).isEqualTo(newMember1.getBirth());
// 		assertThat(savedMember1.getLoginId()).isEqualTo(newMember1.getLoginId());
// 		assertThat(savedMember1.getPassword()).isEqualTo(newMember1.getPassword());
//
// 		assertThat(savedMember2.getUniversityId()).isEqualTo(newMember2.getUniversityId());
// 		assertThat(savedMember2.getStudentId()).isEqualTo(newMember2.getStudentId());
// 		assertThat(savedMember2.getDepartment()).isEqualTo(newMember2.getDepartment());
// 		assertThat(savedMember2.getName()).isEqualTo(newMember2.getName());
// 		assertThat(savedMember2.getPhone()).isEqualTo(newMember2.getPhone());
// 		assertThat(savedMember2.getLoginId()).isEqualTo(newMember2.getLoginId());
// 		assertThat(savedMember2.getEmail()).isEqualTo(newMember2.getEmail());
// 		assertThat(savedMember2.getBirth()).isEqualTo(newMember2.getBirth());
// 		assertThat(savedMember2.getLoginId()).isEqualTo(newMember2.getLoginId());
// 		assertThat(savedMember2.getPassword()).isEqualTo(newMember2.getPassword());
// 	}
//
// 	@Test
// 	public void MemberServiceFailByDuplicateStudentIdTest() {
// 		// Given
// 		Member newMember1 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221415")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345678")
// 			.email("test@test.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test1234")
// 			.password("@@test1234")
// 			.build();
//
// 		Member newMember2 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221415")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345678")
// 			.email("test@test.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test123")
// 			.password("@@test1234")
// 			.build();
//
// 		// When
// 		memberService.signup(newMember1);
//
// 		// Then
// 		Assertions.assertThrows(DuplicateStudentIdException.class, () -> memberService.signup(newMember2));
// 	}
//
// 	@Test
// 	public void MemberServiceFailByDuplicateLoginIdTest() {
// 		// Given
// 		Member newMember1 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221415")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345678")
// 			.email("test@test.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test1234")
// 			.password("@@test1234")
// 			.build();
//
// 		Member newMember2 = Member
// 			.builder()
// 			.universityId(1L)
// 			.studentId("20221414")
// 			.department("컴퓨터공학과")
// 			.name("홍길동")
// 			.phone("01012345678")
// 			.email("test@test.com")
// 			.birth(LocalDate.of(1999, 01, 01))
// 			.loginId("test1234")
// 			.password("@@test1234")
// 			.build();
//
// 		// When
// 		memberService.signup(newMember1);
//
// 		// Then
// 		Assertions.assertThrows(DuplicateLoginIdException.class, () -> memberService.signup(newMember2));
// 	}
// }
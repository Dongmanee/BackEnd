package com.dongmanee.domain.member.controller.apidoc;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.dongmanee.domain.email.dto.request.RequestEmailAuthCode;
import com.dongmanee.domain.email.dto.request.RequestVerifyAuthCode;
import com.dongmanee.domain.member.dto.request.RequestUpdateEmail;
import com.dongmanee.domain.member.dto.request.RequestUpdateMemberDetails;
import com.dongmanee.domain.member.dto.request.RequestUpdatePassword;
import com.dongmanee.domain.member.dto.response.MainPageMemberClubDto;
import com.dongmanee.domain.member.dto.response.ResponseMember;
import com.dongmanee.domain.member.dto.response.ResponseMemberDetails;
import com.dongmanee.domain.security.domain.CustomUserDetails;
import com.dongmanee.global.utils.ApiResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "멤버", description = "멤버 API 명세")
public interface MemberControllerApiDocs {

	@Operation(summary = "멤버 정보 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "멤버 정보 조회 성공",
					value = """
						{
							"status":200,
							"message":"멤버 정보 조회 성공",
							"data": {
								"university": "한밭대학교",
								"department": "컴퓨터공학과",
								"name": "홍길동"
								"profileImageUrl": null
							}
						}
							"""))),
	})
	ApiResult<ResponseMember> findMemberById(Long id);

	@Operation(summary = "멤버 세부 정보 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "멤버 정보 조회 성공",
					value = """
						{
							"status":200,
							"message":"멤버 정보 조회 성공",
							"data": {
								"university": "한밭대학교",
								"studentId": "20181010",
								"department": "컴퓨터공학과",
								"name": "홍길동",
								"phone": "010-1234-5678",
								"email": "example@example.org",
								"birth": "1999-01-01",
								"profileImageUrl": null
							}
						}
							"""))),
	})
	ApiResult<ResponseMemberDetails> findMemberDetailsById(UserDetails userDetails);

	@Operation(summary = "멤버 세부 정보 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "멤버 정보 수정 성공",
					value = """
						{
							"status":200,
							"message":"멤버 정보 수정 성공",
							"data": {
								"university": "한밭대학교",
								"studentId": "20181010",
								"department": "컴퓨터공학과",
								"name": "홍길동",
								"phone": "010-1234-5678",
								"email": "example@example.org",
								"birth": "1999-01-01",
								"profileImageUrl": null
							}
						}
							"""))),
	})
	ApiResult<ResponseMemberDetails> updateMemberDetails(UserDetails userDetails, RequestUpdateMemberDetails request);

	@Operation(summary = "멤버 비밀번호 변경")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "멤버 비밀번호 변경 성공",
					value = """
						{
							"status":204,
							"message":"비밀번호 변경 성공",
							"data": null
						}
							"""))),
	})
	ApiResult<?> updateMemberPassword(UserDetails userDetails, RequestUpdatePassword request);

	@Operation(summary = "이메일 인증 코드 요청")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 코드 전송 성공",
					value = """
						{
							"status":204,
							"message":"인증 코드 전송 성공",
							"data": null
						}
							"""))),
		@ApiResponse(responseCode = "400",
			description = "요청 에러",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = {
					@ExampleObject(name = "인증 메일 발송 실패",
						value = """
							{
							    "status":400,
							    "message":"인증 메일 발송 실패",
							    "data": null
							}
								""")
				}))
	})
	ApiResult<?> sendEmailAuthCode(RequestEmailAuthCode requestEmailAuthCode);

	@Operation(summary = "이메일 인증 코드 검증")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 성공",
					value = """
						{
							"status":204,
							"message":"이메일 인증 성공",
							"data": null
						}
							"""))),
		@ApiResponse(responseCode = "401",
			description = "요청 실패",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = {
					@ExampleObject(name = "인증 실패",
						value = """
							{
								"status":401,
								"message":"이메일 인증 실패",
								"data": null
							}
								""")
				}))
	})
	ApiResult<?> verifyEmailAuthCode(RequestVerifyAuthCode requestVerifyAuthCode);

	@Operation(summary = "멤버 이메일 변경")
	@ApiResponses({
		@ApiResponse(responseCode = "204",
			description = "요청 성공",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "멤버 이메일 변경 성공",
					value = """
						{
							"status":204,
							"message":"이메일 변경 성공",
							"data": null
						}
							"""))),
	})
	ApiResult<?> updateMemberEmail(UserDetails userDetails, RequestUpdateEmail request);

	@Operation(summary = "로그인 유저의 가입 클럽 목록 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "200",
			description = "조회 성공 - 클럽 이름, 클럽 메인 이미지",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "클럽조회성공",
					value = """
						{
							"status": 200,
							"message": "조회에 성공하였습니다.",
							"data": [
								{
								"clubId": 1,
								"name": "clubName",
								"clubMainImageUrl":"url or null"
								}
							]
						}
												""")
			)),
		@ApiResponse(responseCode = "401",
			description = "인증에 실패하였습니다. - JWT토큰 혹은 ClubUserRole",
			content = @Content(schema = @Schema(implementation = ApiResult.class),
				examples = @ExampleObject(name = "인증 실패",
					value = """
						{
							"status": 401,
							"message": "인증에 실패하였습니다.",
							"daa": null
						}
												""")
			))
	})
	ApiResult<List<MainPageMemberClubDto>> clubJoinLists(CustomUserDetails userDetails);

}

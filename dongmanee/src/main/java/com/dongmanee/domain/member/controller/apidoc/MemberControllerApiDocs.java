package com.dongmanee.domain.member.controller.apidoc;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;

import com.dongmanee.domain.club.dto.response.MemberJoinedClubResponseDto;
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
	ApiResult<ResponseMember> findMemberById(@PathVariable("member-id") Long id);

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
	ApiResult<ResponseMemberDetails> findMemberDetailsById(@AuthenticationPrincipal UserDetails userDetails);

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
	ApiResult<List<MemberJoinedClubResponseDto>> clubJoinLists(CustomUserDetails userDetails);

}

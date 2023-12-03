package com.dongmanee.domain.club.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestCreateClub {
	@Schema(description = "카테고리의 고유 식별자", example = "1")
	@NotNull(message = "카테고리 id가 입력되지 않았습니다")
	private Long categoryId;
	@Schema(description = "생성할 동아리의 이름", example = "Dongmanee")
	@NotEmpty(message = "동아리의 이름을 지정하여야 합니다.")
	private String name;
	@Schema(description = "생성할 동아리의 설명", example = "Dongmanee Description", nullable = true)
	private String description;
	@Schema(description = "생성할 동아리의 메인 이미지 Url", example = "imageUrl", nullable = true)
	private String clubMainImageUrl;
	@Schema(description = "생성할 동아리의 배경 이미지 Url", example = "imageUrl", nullable = true)
	private String clubBackgroundImageUrl;
	@Schema(description = "생성할 동아리의 주소", example = "Daejeon, Hakasero ...", nullable = true)
	private String address;
}

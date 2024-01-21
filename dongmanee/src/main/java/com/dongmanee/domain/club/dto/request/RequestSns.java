package com.dongmanee.domain.club.dto.request;

import org.hibernate.validator.constraints.URL;

import com.dongmanee.domain.club.enums.ClubSnsType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
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
public class RequestSns {
	@Schema(description = "생성, 수정할 클럽의 Sns 제목", example = "String type Club Sns Title")
	@NotEmpty
	private ClubSnsType title;
	@Schema(description = "생성, 수정할 클럽의 Sns Url", example = "String type Club Sns Url")
	@NotEmpty
	@URL
	private String url;
}

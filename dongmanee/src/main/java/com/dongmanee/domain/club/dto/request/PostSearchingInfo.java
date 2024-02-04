package com.dongmanee.domain.club.dto.request;

import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

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
public class PostSearchingInfo {
	private Long clubId;
	private ClubPostCategoryDetails postCategory;
	private Long cursor;
	private Integer pageSize;
}

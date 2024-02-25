package com.dongmanee.domain.club.dto.request;

import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;

import lombok.Data;

@Data
public class CreateClubPostRequest {
	private ClubPostCategoryDetails clubPostCategory;
	private String postTitle;
	private String postContent;
}

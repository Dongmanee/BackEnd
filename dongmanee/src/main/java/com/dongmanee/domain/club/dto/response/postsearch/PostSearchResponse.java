package com.dongmanee.domain.club.dto.response.postsearch;

import java.time.LocalDateTime;

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
public class PostSearchResponse {
	private Long postId;
	private String postTitle;
	private LocalDateTime postCreatedAt;
	private String postBody;
	private String postCategoryName;
	private PostWriter postWriter;
	private Long postLikesNum;
	private Long postCommentNum;
	private Boolean isLike;
}

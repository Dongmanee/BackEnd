package com.dongmanee.domain.club.dto.request;

import java.time.LocalDateTime;

import com.dongmanee.domain.club.enums.PostCategory;

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
	private PostCategory postCategory;
	private LocalDateTime cursor;
	private Integer pageSize;
}

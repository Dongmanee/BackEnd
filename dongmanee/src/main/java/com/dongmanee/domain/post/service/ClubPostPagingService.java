package com.dongmanee.domain.post.service;

import java.util.List;

import com.dongmanee.domain.club.dto.request.PostSearchingInfo;
import com.dongmanee.domain.post.domain.Post;

public interface ClubPostPagingService {
	List<Post> pagingDivider(PostSearchingInfo request);

}

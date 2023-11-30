package com.dongmanee.domain.club.controller.port;

import com.dongmanee.domain.club.domain.ClubCategory;

public interface ClubControllerClubCategoryService {
	ClubCategory findById(Long categoryId);
}

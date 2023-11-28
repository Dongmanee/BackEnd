package com.dongmanee.domain.member.controller.port;

import java.util.Optional;

import com.dongmanee.domain.university.domain.University;

public interface MemberControllerUniversityService {
	Optional<University> findById(Long universityId);
}

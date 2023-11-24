package com.dongmanee.domain.club.service;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.member.domain.Member;

public interface ClubService {
	void createClub(Club club, Member member);
}

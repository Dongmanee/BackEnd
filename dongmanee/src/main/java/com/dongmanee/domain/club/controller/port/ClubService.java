package com.dongmanee.domain.club.controller.port;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.member.domain.Member;

public interface ClubService {
	void createClub(Club club, Member member);
}

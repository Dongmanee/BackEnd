package com.dongmanee.domain.club.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResponseClubSchedule {
	Long id;
	LocalDateTime startTime;
	LocalDateTime endTime;
	boolean isAllDay;
	String location;
	String title;
	String description;
	Integer cost;
	Integer numberParticipants;
}
package com.dongmanee.domain.club.domain;

import java.time.LocalDateTime;

import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClubSchedule extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "club_id")
	@NotNull
	private Club club;

	@NotNull
	private LocalDateTime startTime;

	private LocalDateTime endTime;

	@NotNull
	private boolean isAllDay;

	private String location;

	@NotNull
	private String title;

	private String description;

	private int cost;

	private int numberParticipants;
}

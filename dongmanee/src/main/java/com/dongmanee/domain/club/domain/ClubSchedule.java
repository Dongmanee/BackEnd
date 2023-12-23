package com.dongmanee.domain.club.domain;

import java.time.LocalDateTime;

import com.dongmanee.domain.club.dto.request.RequestUpdateClubSchedule;
import com.dongmanee.domain.club.exception.IllegalTimeRangeException;
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

	private Integer cost;

	private Integer numberParticipants;

	public void update(RequestUpdateClubSchedule request) {
		if (request.getStartTime() != null) {
			this.startTime = request.getStartTime();
		}
		if (request.getIsAllDay() != null) {
			this.isAllDay = request.getIsAllDay();
			if (this.isAllDay) {
				this.endTime = null;
			}
		}
		if (!this.isAllDay && request.getEndTime() != null) {
			if (this.getStartTime().isAfter(request.getEndTime())) {
				throw new IllegalTimeRangeException();
			} else {
				this.endTime = request.getEndTime();
			}
		}
		if (request.getTitle() != null) {
			this.title = request.getTitle();
		}
		if (request.getLocation() != null) {
			this.location = request.getLocation().get();
		}
		if (request.getDescription() != null) {
			this.description = request.getDescription().get();
		}
		if (request.getCost() != null) {
			this.cost = request.getCost().get();
		}
		if (request.getNumberParticipants() != null) {
			this.numberParticipants = request.getNumberParticipants().get();
		}
	}
}

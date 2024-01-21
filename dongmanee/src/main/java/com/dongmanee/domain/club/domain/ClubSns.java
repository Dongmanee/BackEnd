package com.dongmanee.domain.club.domain;

import org.hibernate.validator.constraints.URL;

import com.dongmanee.domain.club.enums.ClubSnsType;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "club_sns")
public class ClubSns extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	private Club club;

	@NotNull
	private ClubSnsType title;
	@NotEmpty
	@URL
	private String url;

	public void addClub(Club club) {
		this.club = club;
		club.getClubSns().add(this);
	}

	public void editClubSns(ClubSns clubSns) {
		if (clubSns.getTitle() != null) {
			this.title = clubSns.getTitle();
		}
		if (clubSns.getUrl() != null) {
			this.url = clubSns.getUrl();
		}
	}
}

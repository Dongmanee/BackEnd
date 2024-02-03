package com.dongmanee.domain.club.domain;

import java.util.List;

import com.dongmanee.domain.university.domain.University;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "club")
public class Club extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;
	@OneToMany(mappedBy = "club")
	private List<ClubUser> clubUsers;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ClubCategory category;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClubSns> clubSns;
	private Integer applicationId;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClubPostCategory> clubPostCategories;
	@NotNull
	private String name;
	private String description;
	private String clubMainImageUrl;
	private String clubBackgroundImageUrl;
	private String address;
	@NotNull
	private Boolean isDeleted;

	// list 제외 모든 데이터 수정
	public void editClub(Club club) {
		if (club.getCategory() != null) {
			this.category = club.getCategory();
		}
		if (club.getApplicationId() != null) {
			this.applicationId = club.getApplicationId();
		}
		if (club.getName() != null) {
			this.name = club.getName();
		}
		if (club.getDescription() != null) {
			this.description = club.getDescription();
		}
		if (club.getClubMainImageUrl() != null) {
			this.clubMainImageUrl = club.getClubMainImageUrl();
		}
		if (club.getClubBackgroundImageUrl() != null) {
			this.clubBackgroundImageUrl = club.getClubBackgroundImageUrl();
		}
		if (club.getAddress() != null) {
			this.address = club.getAddress();
		}
		if (club.getIsDeleted() != null) {
			this.isDeleted = club.getIsDeleted();
		}
	}
}


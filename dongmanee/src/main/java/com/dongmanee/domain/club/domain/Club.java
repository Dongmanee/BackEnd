package com.dongmanee.domain.club.domain;

import java.util.ArrayList;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;
	@OneToMany(mappedBy = "club")
	private List<ClubUser> clubUsers = new ArrayList<>();
	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ClubCategory category;
	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ClubSns> clubSns = new ArrayList<>();
	private Integer applicationId;
	@NotNull
	private String name;
	private String description;
	private String clubMainImageUrl;
	private String address;
	@NotNull
	private Boolean isDeleted;
}

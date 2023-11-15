package com.dongmanee.domain.club.domain;

import java.util.ArrayList;
import java.util.List;

import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "clubs")
public class Club extends BaseEntity {
	@Id
	private Long id;

	//TODO: 이 위치에 universities 와 연관관계 설정 필요

	@OneToMany(mappedBy = "club")
	private List<ClubUser> clubUsers = new ArrayList<>();
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ClubCategory clubCategory;
	@OneToMany(mappedBy = "club")
	private List<ClubSns> clubSns = new ArrayList<>();

	@NotNull
	private Integer applicationId;
	@NotNull
	private String name;
	private String description;
	private String clubMainImageUrl;
	private String address;
	@NotNull
	private Boolean isDeleted;
}

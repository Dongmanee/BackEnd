package com.dongmanee.domain.post.domain;

import java.util.List;

import com.dongmanee.domain.club.domain.Club;
import com.dongmanee.domain.post.enums.ClubPostCategoryDetails;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "club_post_category")
public class ClubPostCategory extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "club_id")
	private Club club;

	@OneToMany(mappedBy = "category")
	private List<ClubPost> clubPosts;

	@Enumerated(EnumType.STRING)
	private ClubPostCategoryDetails name;
	private Boolean isPublic;

}

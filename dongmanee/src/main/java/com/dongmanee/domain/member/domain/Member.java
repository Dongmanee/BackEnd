package com.dongmanee.domain.member.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.domain.university.domain.University;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "member")
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "member")
	private List<ClubUser> clubUsers = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(unique = true)
	private String studentId;

	private String department;

	private String name;

	private String phone;

	private String email;

	private LocalDate birth;

	private String profileImageUrl;

	@Column(unique = true)
	private String loginId;

	private String password;

	public void updateRole(Role role) {
		this.role = role;
	}

	public void updateEmail(String email) {
		this.email = email;
	}
}

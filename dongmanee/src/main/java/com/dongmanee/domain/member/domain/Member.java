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
@Table(name = "member")
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "member")
	private List<ClubUser> clubUsers = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "university_id")
	private University university;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Role role;

	@NotNull
	@Column(unique = true)
	private String studentId;

	@NotNull
	private String department;

	@NotNull
	private String name;

	@NotNull
	private String phone;

	@NotNull
	private String email;

	@NotNull
	private LocalDate birth;

	private String profileImageUrl;

	private String password;

	public void updateRole(Role role) {
		this.role = role;
	}

	public void updateEmail(String email) {
		this.email = email;
	}

	public void updateMember(String department, String phone, LocalDate birth, String profileImageUrl) {
		if (department != null) {
			this.department = department;
		}
		if (phone != null) {
			this.phone = phone;
		}
		if (birth != null) {
			this.birth = birth;
		}
		if (profileImageUrl != null) {
			this.profileImageUrl = profileImageUrl;
		}
	}

	public void updatePassword(String encodedNewPassword) {
		this.password = encodedNewPassword;
	}
}

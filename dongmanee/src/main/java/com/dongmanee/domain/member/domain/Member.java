package com.dongmanee.domain.member.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dongmanee.domain.club.domain.ClubUser;
import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@NotNull
	private Long universityId;

	@NotNull
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

	@Column(unique = true)
	private String loginId;

	private String password;

	public void changeRole(Role role) {
		this.role = role;
	}
}

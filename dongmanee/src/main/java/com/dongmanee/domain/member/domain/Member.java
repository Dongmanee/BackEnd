package com.dongmanee.domain.member.domain;

import java.time.LocalDate;

import com.dongmanee.domain.member.enums.Role;
import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "member")
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long universityId;

	@Column(nullable = false)
	private Role role;

	@Column(nullable = false, unique = true)
	private String studentId;

	@Column(nullable = false)
	private String department;

	@Column(nullable = false)
	private String name;

	@Column(unique = true)
	private String phone;

	@Column(unique = true)
	private String email;

	private String profileImageUrl;

	private LocalDate birth;

	@Column(unique = true)
	private String loginId;

	private String password;

	public void changeRole(Role role) {
		this.role = role;
	}
}

package com.dongmanee.domain.university.domain;

import com.dongmanee.global.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class University extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotNull
	@Column(unique = true)
	String name;
}

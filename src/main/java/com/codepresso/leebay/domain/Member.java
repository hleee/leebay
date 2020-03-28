package com.codepresso.leebay.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	@Temporal(value = TemporalType.DATE)
	private Date birthday;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false, length = 50)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Transient
	@Column(nullable = false)
	private String passwordReentered;

	@Column(nullable = false)
	private String sex;

	private LocalDateTime createdAt;

	@PrePersist
	protected void createdAt() {
		this.createdAt = LocalDateTime.now();
	}

}
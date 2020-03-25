package com.codepresso.leebay.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private long id;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false)
	private Date birthday;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false, length = 50)
	private String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Transient
	@Column(nullable = false)
	private String passwordReentered;

	private String sex;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

}
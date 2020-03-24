package com.codepresso.leebay.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "memberId", "productId" }) })
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(nullable = false)
	private long memberId;

	@Column(nullable = false)
	private long productId;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

}

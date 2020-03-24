package com.codepresso.leebay.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length =200)
	private String image;

	@Column(nullable = false, length = 50)
	private String description;

	@Column(nullable = false)
	private long originalPrice;

	private long discountedPrice;

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

	private Boolean isAdded;

}

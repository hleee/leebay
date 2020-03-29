package com.codepresso.leebay.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long id;

	@Column(nullable = false, length = 30)
	private String name;

	@Column(nullable = false, length = 200)
	private String image;

	@Column(nullable = false, length = 50)
	private String description;

	@Column(nullable = false)
	private long originalPrice;

	private Long discountedPrice;

	private LocalDateTime createdAt;

	@Transient
	private Boolean isAdded;

	@PrePersist
	protected void createdAt() {
		this.createdAt = LocalDateTime.now();
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	@Transient
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", referencedColumnName = "id", insertable = false, updatable = false, nullable = true)
	private Basket basket;

}

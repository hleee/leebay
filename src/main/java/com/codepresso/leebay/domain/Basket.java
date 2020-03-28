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
import javax.persistence.Table;
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

	private LocalDateTime createdAt;

	@PrePersist
	protected void createdAt() {
		this.createdAt = LocalDateTime.now();
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "productInBasketId", referencedColumnName = "id", insertable = false, updatable = false)
//	private Product product;

}

package com.codepresso.leebay.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private long id;
	private String name;
	private String image;
	private String description;
	private long originalPrice;
	private long discountedPrice;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;
	private Boolean isAdded;

}

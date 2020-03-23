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
public class Basket {

	@Id
	@GeneratedValue
	private long id;
	private long memberID;
	private long productID;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdAt;

}

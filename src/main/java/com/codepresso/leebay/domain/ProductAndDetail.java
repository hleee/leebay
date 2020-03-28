package com.codepresso.leebay.domain;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class ProductAndDetail {

	private Long id;
	private String name;
	private String image;
	private String description;
	private Long originalPrice;
	private Long discountedPrice;
	private LocalDateTime createdAt;
	private Boolean isAdded;
	private Object detail;

}

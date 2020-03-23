package com.codepresso.leebay.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class ProductAndBasketVO {

	private long memberID;
	private long offsetValue;

}

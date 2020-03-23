package com.codepresso.leebay.domain;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
public class ResponseVO {

	private int code;
	private String message;
	private Object data;

}

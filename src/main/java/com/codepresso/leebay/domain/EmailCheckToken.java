package com.codepresso.leebay.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@Entity
public class EmailCheckToken {

	@Id
	@Column(nullable = false, length = 64)
	private String emailCheckToken;
	@Column(nullable = false, length = 50)
	private String email;
	private LocalDateTime createdAt;

	@PrePersist
	protected void createdAt() {
		this.createdAt = LocalDateTime.now();
	}

}

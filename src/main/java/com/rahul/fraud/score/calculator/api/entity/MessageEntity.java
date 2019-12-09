package com.rahul.fraud.score.calculator.api.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "message")
public class MessageEntity implements Serializable{

	
	private static final long serialVersionUID = 7632289132948131684L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "message")
	private String message;

	@NotNull
	@Column(name = "recipient_id")
	private String recipientId;

	@NotNull
	@Column(name = "sender_id")
	private String senderId;
	
	@Column
	@CreationTimestamp
    private LocalDateTime createDateTime;

}

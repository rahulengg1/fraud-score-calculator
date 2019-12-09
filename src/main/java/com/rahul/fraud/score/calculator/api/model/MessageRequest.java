package com.rahul.fraud.score.calculator.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class MessageRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3554151539490371478L;

	@JsonProperty("senderId")
	@NotNull(message="Sender Id cannot be null")
	@NotBlank(message="Sender Id cannot be empty")
	private String senderId;

	@JsonProperty("recipientId")
	@NotNull(message="Recipient Id cannot be null")
	@NotBlank(message="Sender Id cannot be empty")
	private String recipientId;

	@JsonProperty("message")
	@NotNull(message="Message parameter cannot be null")
	@NotBlank(message="Message parameter cannot be empty")
	private String message;

}

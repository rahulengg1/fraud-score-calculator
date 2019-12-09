package com.rahul.fraud.score.calculator.api.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FraudResponse implements Serializable {

	private static final long serialVersionUID = 6284604807105053282L;

	@JsonProperty("score")
	private BigDecimal score;

	

}

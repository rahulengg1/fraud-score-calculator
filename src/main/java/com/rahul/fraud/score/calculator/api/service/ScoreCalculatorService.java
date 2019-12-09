package com.rahul.fraud.score.calculator.api.service;

import com.rahul.fraud.score.calculator.api.model.FraudResponse;
import com.rahul.fraud.score.calculator.api.model.MessageRequest;

public interface ScoreCalculatorService {
	
	public FraudResponse getScore(MessageRequest messageRequest);

}

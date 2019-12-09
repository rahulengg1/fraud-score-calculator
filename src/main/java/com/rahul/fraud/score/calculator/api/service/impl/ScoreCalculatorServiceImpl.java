package com.rahul.fraud.score.calculator.api.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.rahul.fraud.score.calculator.api.configuration.AppConfig;
import com.rahul.fraud.score.calculator.api.entity.MessageEntity;
import com.rahul.fraud.score.calculator.api.model.FraudResponse;
import com.rahul.fraud.score.calculator.api.model.MessageRequest;
import com.rahul.fraud.score.calculator.api.repository.MessageRepository;
import com.rahul.fraud.score.calculator.api.service.ScoreCalculatorService;

@Service
public class ScoreCalculatorServiceImpl implements ScoreCalculatorService {


	

	private MessageRepository messageRepository;

	private AppConfig appConfig;

	private static final int TEN_MULTIPLER = 10;
	private static final ModelMapper mapper = new ModelMapper();

	public ScoreCalculatorServiceImpl(MessageRepository messageRepository, AppConfig appConfig) {
		super();
		this.messageRepository = messageRepository;
		this.appConfig = appConfig;
	}

	@Override
	public FraudResponse getScore(MessageRequest messageRequest) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		MessageEntity messageEntity = mapper.map(messageRequest, MessageEntity.class);
		messageRepository.save(messageEntity);
		FraudResponse fraudResponse = new FraudResponse();
		fraudResponse.setScore(calculateScore(messageRequest));
		return fraudResponse;

	}

	private BigDecimal calculateScore(MessageRequest messageRequest) {
		long score = 0;

		// calculate score from database
		if (getDbCount(messageRequest) > 5) {
			score += 100;
		}

		// calculate score for word occurrence with one point
		score += count(messageRequest.getMessage(), appConfig.getCountValueOne());
		
		/// calculate score for word occurrence with ten points
		for (String stringValue : appConfig.getCountValueTen()) {
			score += count(messageRequest.getMessage(), stringValue) * TEN_MULTIPLER;
		}
		
		BigDecimal totalScore = new BigDecimal(score);

		return totalScore;
	}

	private int count(String str, String subStr) {
		return (str.length() - str.replace(subStr, "").length()) / subStr.length();
	}

	private long getDbCount(MessageRequest messageRequest) {
		LocalDateTime localDateTime = LocalDateTime.now();
		localDateTime = localDateTime.minusHours(2);
		return messageRepository.countByRecipientIdAndSenderId(messageRequest.getRecipientId(),
				messageRequest.getSenderId(), localDateTime);
	}

}

package com.rahul.fraud.score.calculator.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rahul.fraud.score.calculator.api.configuration.AppConfig;
import com.rahul.fraud.score.calculator.api.model.FraudResponse;
import com.rahul.fraud.score.calculator.api.model.MessageRequest;
import com.rahul.fraud.score.calculator.api.repository.MessageRepository;
import com.rahul.fraud.score.calculator.api.service.impl.ScoreCalculatorServiceImpl;

@SpringBootTest
class MessageApiControllerTest {

	@Autowired
	private MessageRepository messageRepository;
	
	
	@Autowired
	private AppConfig appConfig;

	
	private MessageApiController messageApiController;

	private ScoreCalculatorServiceImpl scoreCalculatorServiceImpl;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		scoreCalculatorServiceImpl = new ScoreCalculatorServiceImpl(messageRepository,appConfig);
		messageApiController = new MessageApiController(scoreCalculatorServiceImpl);
	}

	@Test
	void whenValidInput_thenReturns200() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("sender@test.com");

		assertEquals(FraudResponse.class, messageApiController.messagePost(messageRequest).getBody().getClass());
		assertEquals(1,  messageApiController.messagePost(messageRequest).getBody().getScore().intValue());
	}
	
	
	

}

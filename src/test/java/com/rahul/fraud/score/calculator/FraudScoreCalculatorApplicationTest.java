package com.rahul.fraud.score.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahul.fraud.score.calculator.api.configuration.AppConfig;
import com.rahul.fraud.score.calculator.api.controller.HomeController;
import com.rahul.fraud.score.calculator.api.controller.MessageApiController;
import com.rahul.fraud.score.calculator.api.model.MessageRequest;
import com.rahul.fraud.score.calculator.api.repository.MessageRepository;
import com.rahul.fraud.score.calculator.api.service.impl.ScoreCalculatorServiceImpl;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { HomeController.class, MessageApiController.class, ScoreCalculatorServiceImpl.class,
		AppConfig.class })
@WebMvcTest
class FraudScoreCalculatorApplicationTest {

	@Autowired
	private MockMvc mockMvc;

	private static final String API_ENDPOINT = "/message";

	@MockBean
	private MessageRepository messageRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();
	}

	@Test
	void whenValidInput_thenReturns200() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isOk());

	}

	@Test
	void whenEmptyMessage_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("");
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void whenEmptySender_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void whenEmptyRecipient_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void whenNullMessage_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("Test message");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void whenNullSender_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("receiver@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void whenNullRecipient_thenReturns400() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest());

	}

	@Test
	void testScoreCalculator_isOk() throws Exception {
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setMessage("<a> Test message");
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isOk()).andExpect(jsonPath("score").value(1));
	}

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("swagger-ui.html"));
	}

	@Test
	void whenInvalidInput_thenException() throws Exception {

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setRecipientId("receiver@test.com");
		messageRequest.setSenderId("sender@test.com");

		mockMvc.perform(post(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).content(objectMapper.writeValueAsString(messageRequest)))
				.andExpect(status().isBadRequest()).andReturn();

	}
}

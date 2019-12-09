package com.rahul.fraud.score.calculator.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rahul.fraud.score.calculator.api.model.ErrorResponse;
import com.rahul.fraud.score.calculator.api.model.FraudResponse;
import com.rahul.fraud.score.calculator.api.model.MessageRequest;
import com.rahul.fraud.score.calculator.api.service.ScoreCalculatorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value = "message")
public class MessageApiController {

	private ScoreCalculatorService scoreCalculatorService;

	public MessageApiController(ScoreCalculatorService scoreCalculatorService) {
		this.scoreCalculatorService = scoreCalculatorService;
	}

	@ApiOperation(value = "Check a message for fraud", response = FraudResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response", response = FraudResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = ErrorResponse.class),
			@ApiResponse(code = 404, message = "Client Error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Server Error", response = ErrorResponse.class) })
	@PostMapping(value = "/message", produces = { "application/json" }, consumes = { "application/json" })
	public ResponseEntity<FraudResponse> messagePost(@Valid @RequestBody MessageRequest body) {
		FraudResponse response = scoreCalculatorService.getScore(body);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

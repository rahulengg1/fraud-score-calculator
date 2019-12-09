package com.rahul.fraud.score.calculator.api.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class AppConfig {

	@Value("${message.string.count.value.one}")
	private String countValueOne;

	@Value("#{'${message.string.count.value.ten}'.split(',')}")
	private List<String> countValueTen;
}

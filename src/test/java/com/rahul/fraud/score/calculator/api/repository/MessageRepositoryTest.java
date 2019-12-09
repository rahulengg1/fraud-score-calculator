package com.rahul.fraud.score.calculator.api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rahul.fraud.score.calculator.api.entity.MessageEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MessageRepositoryTest {

	@Autowired
	private MessageRepository messageRepository;

	@MockBean
	private MessageEntity messageEntity;

	@Test
	public void injectedComponentsAreNotNull() {

		assertThat(messageEntity).isNotNull();
		assertThat(messageRepository).isNotNull();
	}
	
	@Test
	public void checkDbOperation(){
		
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setSenderId("sender@test.com");
		messageEntity.setRecipientId("recipient@test.com");
		messageEntity.setId(1L);
		messageEntity.setMessage("<a> test message");
		messageRepository.save(messageEntity);
		
		MessageEntity messageEntityStored = messageRepository.findById(1L).get();
		
		assertEquals(messageEntity.getMessage(), messageEntityStored.getMessage());
		assertEquals(messageEntity.getSenderId(), messageEntityStored.getSenderId());
		assertEquals(messageEntity.getRecipientId(), messageEntityStored.getRecipientId());
		
	}
}

package com.rahul.fraud.score.calculator.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rahul.fraud.score.calculator.api.entity.MessageEntity;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

	@Query(value = "select count(*) from message where recipient_id = :recipientId "
			+ "and sender_id = :senderId "
			+ "and create_date_time > :currentDateTime", nativeQuery = true)
	long countByRecipientIdAndSenderId(@Param("recipientId") String recipientId,
			@Param("senderId") String senderId, @Param("currentDateTime") LocalDateTime currentDateTime);
}

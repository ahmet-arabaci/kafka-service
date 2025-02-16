package com.kafkaservice.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kafkaservice.dto.UserDto;

@Service
public class KafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics = "topic1", groupId = "KafkaConsumerGroup")
	public void consume(String message) {
		LOGGER.info("Kafka Consumer -> String message received: " + message);
	}
		
	@KafkaListener(topics = "topic2", groupId = "KafkaConsumerGroup")
	public void consume(UserDto message) {
		LOGGER.info("Kafka Consumer -> JSON message received: " + message);
	}
	
}


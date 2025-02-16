package com.kafkaservice.producer;

import java.net.URI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.kafkaservice.dto.UserDto;
import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

@Service
public class KafkaProducer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
	
	private KafkaTemplate<String, String> kafkaStringTemplate;
	private KafkaTemplate<String, UserDto> kafkaJSONTemplate;
	
	public KafkaProducer(KafkaTemplate<String, String> kafkaStringTemplate, 
			KafkaTemplate<String, UserDto> kafkaJSONTemplate) {
		this.kafkaStringTemplate = kafkaStringTemplate;
		this.kafkaJSONTemplate = kafkaJSONTemplate;
	}
		
	public void sendMessage(String message) {
		LOGGER.info("'" + message + "' message is sending...");
		
		kafkaStringTemplate.send("topic1", message);
	}
		
	public void sendMessage(UserDto dto) {
		LOGGER.info("'" + dto + "' message is sending...");
		
		Message<UserDto> message = MessageBuilder
			.withPayload(dto)
			.setHeader(KafkaHeaders.TOPIC, "topic2")
			.build();
		kafkaJSONTemplate.send(message);
	}
	
	public void sendEventMessage() {
		BackgroundEventHandler eventHandler = new KafkaEventHandler(kafkaStringTemplate, "topic3");
		URI uri = URI.create("https://stream.wikimedia.org/v2/stream/recentchange");
		
		EventSource.Builder builder = new EventSource.Builder(uri);
		BackgroundEventSource.Builder eventSource = new BackgroundEventSource.Builder(eventHandler, builder);
		BackgroundEventSource source = eventSource.build();
		source.start();		
	}
	
}




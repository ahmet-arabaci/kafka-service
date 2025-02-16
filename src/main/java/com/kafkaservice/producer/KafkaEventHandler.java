package com.kafkaservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;

public class KafkaEventHandler implements BackgroundEventHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEventHandler.class);
		
	private KafkaTemplate<String, String> kafkaTemplate;
	private String topic;
	
	public KafkaEventHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
		this.kafkaTemplate = kafkaTemplate;
		this.topic = topic;
	}
	
	@Override
	public void onOpen() throws Exception {}

	@Override
	public void onClosed() throws Exception {}

	@Override
	public void onMessage(String event, MessageEvent messageEvent) throws Exception {
		LOGGER.info("Event data: " + messageEvent.getData());
		kafkaTemplate.send(topic, messageEvent.getData());
	}

	@Override
	public void onComment(String comment) throws Exception {}

	@Override
	public void onError(Throwable t) {}

}


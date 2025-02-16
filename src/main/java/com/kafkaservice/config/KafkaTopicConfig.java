package com.kafkaservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic createTopic1() {
		return TopicBuilder.name("topic1").build();
	}
	
	@Bean
	public NewTopic createTopic2() {
		return TopicBuilder.name("topic2").build();
	}
}


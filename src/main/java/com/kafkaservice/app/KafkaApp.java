package com.kafkaservice.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.kafkaservice.producer.KafkaProducer;

/**
 * 1-) Apache Kafka'nın start olması için ZooKeeper start edilir.
 * 2-) Kafka server start edilir.
 * 3-) Consumer start edilir, eventler dinlenmeye başlanır.
 * 3-) Producer start edilir, eventler girilmeye başlanır.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.kafkaservice.*"})
public class KafkaApp implements CommandLineRunner {

	@Autowired
	private KafkaProducer producer;
	
	@Override
	public void run(String... args) throws Exception {
		producer.sendEventMessage();		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaApp.class, args);
	}

}

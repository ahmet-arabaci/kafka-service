package com.kafkaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.kafkaservice.dto.UserDto;
import com.kafkaservice.producer.KafkaProducer;

@RestController
@RequestMapping("/api/")
public class MessageController {
	
	@Autowired
	private KafkaProducer producer;
	
	public MessageController(KafkaProducer producer) {
		this.producer = producer;
	}
	
	@GetMapping("/sendstringmessage")
	public ResponseEntity<String> sendStringMessage(@RequestParam("message") String message) {
		producer.sendMessage(message);
		return new ResponseEntity<String>("'" + message + "' has been sent to the topic!", HttpStatus.OK);
	}
	
	@PostMapping("/sendjsonmessage")
	public ResponseEntity<String> sendJSONMessage(@RequestBody UserDto message) {
		producer.sendMessage(message);
		return new ResponseEntity<String>("'" + message + "' has been sent to the topic!", HttpStatus.OK);
	}
	
}



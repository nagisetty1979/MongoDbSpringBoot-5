package org.nagisetty.controller;

import org.nagisetty.model.Student;
import org.nagisetty.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Producer")
public class ProducerController {
	
	@Autowired
	private KafkaSender sender;
	
	
	@PostMapping
	public ResponseEntity<String> sendDate(@RequestBody Student student){
		
		sender.sendData(student);
		return new ResponseEntity<>(student.toString(),HttpStatus.OK);
	}

}

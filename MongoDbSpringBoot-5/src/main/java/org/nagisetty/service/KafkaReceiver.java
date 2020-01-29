package org.nagisetty.service;

import org.nagisetty.model.Student;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiver {
	private static final org.slf4j.Logger log= LoggerFactory.getLogger(KafkaReceiver.class);

	
	@KafkaListener(topics = "${kafka.topic.name}",groupId = "${kafka.consumer.group.id}")
	public void receiveData(Student student) {
		
		log.info("Student info from receive ",student);
	}

}

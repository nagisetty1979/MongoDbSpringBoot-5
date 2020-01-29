package org.nagisetty.service;

import java.util.HashMap;
import java.util.Map;

import org.nagisetty.model.Student;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;


@Service
public class KafkaSender {
private static final org.slf4j.Logger log= LoggerFactory.getLogger(KafkaSender.class);

@Value("${kafka.topic.name}")
private String topicName;
@Autowired
private KafkaTemplate<String,String> kafakaTemplates;

public void sendData(Student student) {
	Map<String,Object>  headers = new HashMap<String,Object>();
	headers.put(KafkaHeaders.TOPIC, topicName);
	kafakaTemplates.send(new GenericMessage<Student>(student,headers));
	log.info("Student info from sendData ",student);

	
	
	
	
}

}

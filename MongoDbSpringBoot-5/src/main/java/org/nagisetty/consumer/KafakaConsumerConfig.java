package org.nagisetty.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.nagisetty.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafakaConsumerConfig {
	
	@Value("${kafka.boot.server}")
	private String kafkaServer;
	@Value("${kafka.consumer.group.id}")
	private String kafakGroupId;
	
	@Bean
	public ConsumerFactory<String,Student> consumerConfig(){
		System.out.println("The  Consumer Factory");
		Map<String,Object>  config = new HashMap<String,Object>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, kafakGroupId);
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config,null,new JsonDeserializer<Student>(Student.class));
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,Student>> kafkaListenerContainerFactory(){
		
		ConcurrentKafkaListenerContainerFactory<String, Student>  listener = new ConcurrentKafkaListenerContainerFactory();
		listener.setConsumerFactory(consumerConfig());
		return listener;
		
	}
}

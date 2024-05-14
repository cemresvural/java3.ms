package com.turkcell.productservice;

import com.turkcell.common.events.OrderCreatedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableFeignClients
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

	@KafkaListener(topics = {"orderTopic"})
	public void consumeKafkaMessage(OrderCreatedEvent event)
	{
		System.out.println("Kafka message caught.");
	}


}

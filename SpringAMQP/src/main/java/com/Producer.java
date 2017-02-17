package com;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.ProducerConfig;
import com.domain.Customer;

public class Producer {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = new AnnotationConfigApplicationContext(ProducerConfig.class);
		
		AmqpTemplate template = (RabbitTemplate) context.getBean("rabbitTemplate");
		for (int i = 0; i < 20; i++) {
			Customer customer = new Customer("Name"+i, "Msg:" + i);
			System.out.println("Sending: " + customer);
			template.convertAndSend(customer);
			Thread.sleep(5000);
		}
		
	}
}

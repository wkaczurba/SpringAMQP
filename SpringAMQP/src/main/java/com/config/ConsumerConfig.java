package com.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.alert.CustomerAlertHandler;

@Configuration
@Import(SharedConfig.class)
public class ConsumerConfig {	
	// Consumer-specific stuff:
	@Bean
	public CustomerAlertHandler customerAlertHandler() {
		return new CustomerAlertHandler();
	}
	
	@Bean
	public MessageListenerAdapter listenerAdapter(CustomerAlertHandler customerAlertHandler) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(customerAlertHandler);
		adapter.setDefaultListenerMethod("handleCustomerAlert");
		return adapter;
	}
	
	@Bean
	public SimpleMessageListenerContainer simpleMessageListnerContainer(
			ConnectionFactory connectionFactory, 
			MessageListenerAdapter listenerAdapter) {
		
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		//container.setConnectionFactory(connectionFactory);
		container.setQueueNames("customer.alerts");
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	// Consumer-specific: -- MessageListenerAdapter listenerAdapter(); + SimpleMessageListenerContainer container(ConnectionFactory connectionFactory);
}

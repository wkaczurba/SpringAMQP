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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedConfig {
	// TODO: RabbitMQ, etc...
	
	// Shared: ConnectionFactory, amqpAdmin, rabbitTemplate
	//   Rounting/channel related: Queue (spittleAlertQueue) + binding + fanout exchange
	// 
	// Consumer-specific: SimpleMessageListenerContainer + POJO listener (spittleListener)
	
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory cf = new CachingConnectionFactory("localhost");
		return cf;
	}
	
	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}
	
	// Connection-related stuff:
	@Bean
	public FanoutExchange exchange() {
		return new FanoutExchange("customer.alert.exchange");
	}
	@Bean
	public Queue customerAlertQueue() {
		return new Queue("customer.alerts");
	}
	@Bean
	public Binding binding(Queue queue, FanoutExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange);
	}	
}

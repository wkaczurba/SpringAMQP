package com.alert;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.domain.Customer;

public class AlertServiceImpl implements AlertService {

	private RabbitTemplate rabbit; 
	
	@Autowired
	public AlertServiceImpl(RabbitTemplate rabbit) {
		this.rabbit = rabbit;
	}
	
	
	public void sendCustomerAlert(Customer customer) {
		rabbit.convertAndSend("customer.alert.exchange",
				"customer.alerts",
				customer);
	}
}

package com.alert;

import com.domain.Customer;

public class CustomerAlertHandler {

	public void handleCustomerAlert(Customer customer) {
		System.out.println("Received customer: " + customer);
	}
		
}

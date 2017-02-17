package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.config.ConsumerConfig;

public class Consumer {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfig.class);
	}
}

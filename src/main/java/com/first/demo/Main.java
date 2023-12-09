package com.first.demo;

import com.first.demo.customer.entity.Customer;
import com.first.demo.customer.DAO.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		/*
		Never do this
		CustomerService customerService =
				new CustomerService(new CustomerDas());
		CustomerController customerController =
				new CustomerController(customerService);
		*/
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
//		printBeans(applicationContext);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepository customerRepository

	) {
		Customer Ali = new Customer("Ali", "ali@gmail.com", 22);
		Customer Ahmed = new Customer("Ahmed", "ahmed@gmail.com", 25);
		Customer Moamel = new Customer("Moamel", "moamel@gmail.com", 20);
		List<Customer> customer = List.of(Ali, Ahmed, Moamel);

		return args -> {
			customerRepository.saveAll(customer);

	};
	}


//	@Bean
//	public Foo getFoo(){
//		return new Foo("bar");
//	}
//	record Foo(String name){}
//	private static void printBeans(ConfigurableApplicationContext ctx){
//		String[] beanDefinitionNames =
//				ctx.getBeanDefinitionNames();
//
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println(beanDefinitionName);
//		}
//		//print the beans that are created by spring
//	}
	}
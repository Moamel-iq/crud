package com.first.demo.customer.DTO;
import com.first.demo.customer.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDtoMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAge()
        );

    }
}

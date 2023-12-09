package com.first.demo.customer.DAO;

import com.first.demo.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(Integer id);
    Optional<Customer> findByName(String name);

}

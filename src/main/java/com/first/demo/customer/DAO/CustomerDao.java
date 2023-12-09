package com.first.demo.customer.DAO;

import com.first.demo.customer.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {
     List<Customer> selectAllCustomers();
     Optional<Customer> selectCustomerById(Integer id);
     Optional<Customer> selectCustomerByName(String name);

     void insertCustomer(Customer customer);
     boolean existsPersonWithEmail(String email);
     boolean existsPersonWithId(Integer id);

     void deleteCustomer(Integer id);
     void updateCustomer(Integer id, Customer customer);

}

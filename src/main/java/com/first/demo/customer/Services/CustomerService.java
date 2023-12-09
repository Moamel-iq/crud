package com.first.demo.customer.Services;
import com.first.demo.customer.DTO.CustomerDTO;
import com.first.demo.customer.DTO.CustomerDtoMapper;
import com.first.demo.customer.entity.Customer;
import com.first.demo.customer.DTO.CustomerRegistrationRequest;
import com.first.demo.customer.DTO.CustomerUpdateRequest;
import com.first.demo.customer.DAO.CustomerDao;
import com.first.demo.exception.DuplicateResourceException;
import com.first.demo.exception.RequestValidationException;
import com.first.demo.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class  CustomerService {
    private final CustomerDtoMapper customerDtoMapper;
    private final CustomerDao customerDao;

    public CustomerService(CustomerDtoMapper customerDtoMapper, @Qualifier("jpa") CustomerDao customerDao) {
        this.customerDtoMapper = customerDtoMapper;
        this.customerDao = customerDao;
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerDao.selectAllCustomers()
                .stream()
                .map(customerDtoMapper)
                .collect(Collectors.toList());

    }

    public CustomerDTO getCustomer(Integer id) {
        return customerDao.selectCustomerById(id)
                .map(customerDtoMapper)
                .orElseThrow(() -> new ResourceNotFound(
                        "customer with id " + id + " does not exist"
                ));
    }

    public CustomerDTO getCustomerByName(String name) {
        return customerDao.selectCustomerByName(name)
                .map(customerDtoMapper)
                .orElseThrow(() -> new ResourceNotFound("customer with name " + name + " does not exist"));
    }

    public void addCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        String email = customerRegistrationRequest.email();
        if (customerDao.existsPersonWithEmail(email)) {
            throw new DuplicateResourceException("email " + customerRegistrationRequest.email() + " already exists");

        }
        Customer customer = new Customer(
                customerRegistrationRequest.name(),
                customerRegistrationRequest.email(),
                customerRegistrationRequest.age());
        customerDao.insertCustomer(customer);
    }

    public void deleteCustomerById(Integer customerId) {
        if (!customerDao.existsPersonWithId(customerId)) {
            throw new ResourceNotFound("customer with id " + customerId + " does not exist");
        }
        customerDao.deleteCustomer(customerId);

    }

    public void updateCustomer(CustomerUpdateRequest updateRequest, Integer customerId) {
         Customer customer =customerDao.selectCustomerById(customerId)
                .orElseThrow(() -> new ResourceNotFound(
                        "customer with id " + customerId + " does not exist"
                ));
        boolean changes = false;

        if (updateRequest.name() != null && !updateRequest.name().equals(customer.getName())) {
            customer.setName(updateRequest.name());
            changes = true;


        }
        if (updateRequest.email() != null && !updateRequest.email().equals(customer.getEmail()) &&
                !customerDao.existsPersonWithEmail(updateRequest.email())) {
            customer.setEmail(updateRequest.email());
            changes = true;
        }

        if (updateRequest.age() != null && !updateRequest.age().equals(customer.getAge())) {
            customer.setAge(updateRequest.age());
            changes = true;
        }
        if (!changes) {
            throw new RequestValidationException("no changes were made");

        }
           customerDao.updateCustomer(customerId, customer);
    }
}




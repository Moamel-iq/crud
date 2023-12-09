package com.first.demo.customer.DAO;

import com.first.demo.customer.DAO.CustomerDao;
import com.first.demo.customer.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("list")
public class CustomerListDataAccessService implements CustomerDao {
    private static List<Customer> customers;

    static {
        customers= new ArrayList<>();
        Customer ali = new Customer(3,"Ali","ali@gmail.com",20);
        customers.add(ali);
        Customer ahmed = new Customer(4,"Ahmed","ahmed@gmail.com",25);
        customers.add(ahmed);

    }
    @Override
    public List<Customer> selectAllCustomers() {
        return customers;
    }

    @Override
    public Optional<Customer> selectCustomerById(Integer id) {
        return customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Customer> selectCustomerByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }

    @Override
    public void insertCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public boolean existsPersonWithEmail(String email) {
        return customers.stream()
                .anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existsPersonWithId(Integer id) {
        return customers.stream()
                .anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresent(customers::remove);
    }

    @Override
    public void updateCustomer(Integer id, Customer customer) {
        customers.add(customer);

    }

}

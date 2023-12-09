package com.first.demo.customer.controllers;

import com.first.demo.customer.DTO.CustomerDTO;
import com.first.demo.customer.DTO.CustomerRegistrationRequest;
import com.first.demo.customer.Services.CustomerService;
import com.first.demo.customer.DTO.CustomerUpdateRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/all")
    public List<CustomerDTO> customers(){
        return customerService.getAllCustomers();
    }


    @GetMapping(  "/{customerId}")
    public List<CustomerDTO> getCustomer(
            @PathVariable("customerId") Integer customerId
    )
    {
        return List.of(customerService.getCustomer(customerId));
    }


    @GetMapping
    public List<CustomerDTO> getCustomers(
            @RequestParam("customerName") String customerName
    ){
       return List.of(customerService.getCustomerByName(customerName));
    }


    @PostMapping("/add")
    public void addCustomer(@RequestBody CustomerRegistrationRequest request){
        customerService.addCustomer(request);
    }

    @DeleteMapping("/delete")
    public void deleteCustomer(
            @RequestParam("customerId") Integer customerId
    ){
        customerService.deleteCustomerById(customerId);
    }
    @PutMapping("/update")
    public void updateCustomer(
            @RequestParam("customerId") Integer customerId,
            @RequestBody CustomerUpdateRequest customer
    ){
        customerService.updateCustomer(customer,customerId);
    }
}

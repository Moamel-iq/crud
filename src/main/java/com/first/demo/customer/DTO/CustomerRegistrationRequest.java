package com.first.demo.customer.DTO;

public record CustomerRegistrationRequest (
        String name,
        String email,
        Integer age

){

}

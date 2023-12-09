package com.first.demo.customer.DTO;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
){}

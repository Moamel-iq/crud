package com.first.demo.customer.DTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public record CustomerDTO(
        Integer id,
        String name,
        String email,
        Integer age
) {
}

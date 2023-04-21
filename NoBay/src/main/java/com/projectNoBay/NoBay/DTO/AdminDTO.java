package com.projectNoBay.NoBay.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {
    private Long id;
    private String adminName;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}

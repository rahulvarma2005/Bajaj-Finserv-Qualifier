package com.bajaj.Bajaj.Finserv.Qualifier;

// Represents the body for the first POST request

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private String name;
    private String regNo;
    private String email;

}
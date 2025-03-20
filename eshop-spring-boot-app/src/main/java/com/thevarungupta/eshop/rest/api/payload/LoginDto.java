package com.thevarungupta.eshop.rest.api.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Username or email is required")
    private String usernameOrEmail;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}

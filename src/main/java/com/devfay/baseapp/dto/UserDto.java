package com.devfay.baseapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;

    @NotEmpty
    @NotBlank(message = "Username is mandatory")
    private String userName;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @NotBlank(message = "Password is mandatory")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*.])(?=.*[A-Z]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one digit, one special character, and one uppercase letter"
    )
    private String password;
    private boolean active;
}

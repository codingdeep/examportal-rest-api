package com.examportal.exam.payloads;

import com.examportal.exam.models.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    @NotEmpty
    @Size(min = 3, message = "Please enter a name with at least 3 characters")
    private String firstname;
    @NotEmpty
    @Size(min = 3, message = "Please enter a name with at least 3 characters")
    private String lastname;
    @NotEmpty(message = "Please enter a valid email")
    private String email;
    @NotEmpty(message = "Please enter a valid password")
    private String password;
    @NotEmpty(message = "Please enter a valid phone")
    private String phone;
    @NotEmpty(message = "Please enter a valid bio")
    private String bio;

    private boolean status;

    private String profilePic;

    Set<UserRole> userRoles;

}

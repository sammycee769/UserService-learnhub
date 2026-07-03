package com.sammy.userservice.dtos.requests;

import com.sammy.userservice.data.models.Gender;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateUserRequest {
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate dateOfBirth;
    @Pattern(regexp = "^(\\+234|0)(70|8[01]|9[01])[0-9]{8}$", message = "Invalid Nigerian phone number")
    private String phoneNumber;
}
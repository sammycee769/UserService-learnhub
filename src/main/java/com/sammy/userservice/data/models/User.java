package com.sammy.userservice.data.models;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Username is required")
    private String userName;

    @NotBlank(message = "Firstname is required")
    private String firstName;

    @NotBlank(message = "Lastname is required")
    private String lastName;

    private String middleName;

    @NotNull(message = "gender is required")
    private Gender gender;

    @NotBlank(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @Indexed(unique = true)
    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message ="Password is required")
    private String passwordHash;

    @Pattern(regexp = "^(\\+234|0)(70|8[01]|9[01])[0-9]{8}$", message = "Invalid Nigerian phone number")
    private String phoneNumber;

    private Role role;

    private  boolean active = true;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
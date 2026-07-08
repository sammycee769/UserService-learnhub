package com.sammy.userservice.kafka.events;

import com.sammy.userservice.data.models.Gender;
import com.sammy.userservice.data.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisteredEvent {
    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String email;
    private String phoneNumber;
    private Role role;
}
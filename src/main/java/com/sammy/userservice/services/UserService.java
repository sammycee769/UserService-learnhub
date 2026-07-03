package com.sammy.userservice.services;

import com.sammy.userservice.data.models.User;
import com.sammy.userservice.data.repositories.UserRepo;

import com.sammy.userservice.dtos.requests.UpdateUserRequest;
import com.sammy.userservice.dtos.responses.UserResponse;
import com.sammy.userservice.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepository;

    public UserResponse getUserById (String userId) {
        User foundUser = userRepository.findByIdAndActive(userId,true)
                .orElseThrow(()-> new UserNotFoundException("User not found or inactive"));
        return mapToResponse(foundUser);
    }

    public UserResponse getUserByEmail (String email) {
        User foundUser = userRepository.findByEmailAndActive(email,true)
                .orElseThrow(()-> new UserNotFoundException("User not found inactive"));
        return mapToResponse(foundUser);
    }

    public UserResponse getUserByPhoneNumber (String phoneNumber) {
        User foundUser = userRepository.findByPhoneNumberAndActive(phoneNumber,true)
                .orElseThrow(()-> new UserNotFoundException("User not found  inactive"));
        return mapToResponse(foundUser);
    }

    public Page<UserResponse> getAllUsers() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("userName").ascending());
        return userRepository.findAllByActive(true,pageable)
                .map((user->mapToResponse(user)));
    }

    public Page<UserResponse> searchUser(String search) {
        Pageable pageable = PageRequest.of(0,10, Sort.by("userName").ascending());
        return userRepository.searchUsers(search,true,pageable)
                .map((user->mapToResponse(user)));
    }
    public void deleteUserById (String userId) {
        User foundUser = userRepository.findByIdAndActive(userId,true)
                .orElseThrow(()-> new UserNotFoundException("User not found inactive"));
        userRepository.delete(foundUser);
    }

    public UserResponse deactivateUserById (String userId) {
        User foundUser = userRepository.findByIdAndActive(userId,true)
                .orElseThrow(()-> new UserNotFoundException("User not found inactive"));
        foundUser.setActive(false);
        userRepository.save(foundUser);
        return mapToResponse(foundUser);
    }

    public UserResponse activateUserById (String userId) {
        User foundUser = userRepository.findByIdAndActive(userId,false)
                .orElseThrow(()-> new UserNotFoundException("User not found or active"));
        foundUser.setActive(true);
        userRepository.save(foundUser);
        return mapToResponse(foundUser);
    }

    public UserResponse updateUser(String id , UpdateUserRequest request) {
        User foundUser = userRepository.findByIdAndActive(id,true)
                .orElseThrow(()-> new UserNotFoundException("User not found inactive"));
        if(request.getFirstName() != null) foundUser.setFirstName(request.getFirstName());
        if(request.getLastName() != null) foundUser.setLastName(request.getLastName());
        if (request.getUserName() != null) foundUser.setUserName(request.getUserName());
        if(request.getMiddleName() != null) foundUser.setMiddleName(request.getMiddleName());
        if (request.getGender() != null) foundUser.setGender(request.getGender());
        if (request.getDateOfBirth() != null) foundUser.setDateOfBirth(request.getDateOfBirth());
        if (request.getPhoneNumber() != null) foundUser.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(foundUser);
        return mapToResponse(foundUser);

    }

    private UserResponse mapToResponse(User foundUser) {
        return UserResponse.builder()
                .id(foundUser.getId())
                .email(foundUser.getEmail())
                .userName(foundUser.getUserName())
                .firstName(foundUser.getFirstName())
                .lastName(foundUser.getLastName())
                .middleName(foundUser.getMiddleName())
                .gender(foundUser.getGender())
                .dateOfBirth(foundUser.getDateOfBirth())
                .phoneNumber(foundUser.getPhoneNumber())
                .role(foundUser.getRole())
                .createdAt(foundUser.getCreatedAt())
                .build();
    }
}

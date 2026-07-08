package com.sammy.userservice.kafka.consumer;

import com.sammy.userservice.data.models.User;
import com.sammy.userservice.data.repositories.UserRepo;
import com.sammy.userservice.kafka.events.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventConsumer {

    private final UserRepo userRepo;

    @KafkaListener(
            topics = "${kafka.topics.user-registered}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleUserRegistered(UserRegisteredEvent event) {
        log.info("Received UserRegisteredEvent for user: {}", event.getEmail());

        if (userRepo.existsById(event.getId())) {
            log.warn("User already exists with id: {}", event.getId());
            return;
        }

        User user = User.builder()
                .id(event.getId())
                .userName(event.getUserName())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .middleName(event.getMiddleName())
                .gender(event.getGender())
                .dateOfBirth(event.getDateOfBirth())
                .email(event.getEmail())
                .phoneNumber(event.getPhoneNumber())
                .role(event.getRole())
                .active(true)
                .build();

        userRepo.save(user);
        log.info("User profile created successfully for: {}", event.getEmail());
    }
}
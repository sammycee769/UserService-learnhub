package com.sammy.userservice.kafka.producer;

import com.sammy.userservice.kafka.events.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topics.user-updated}")
    private String userUpdatedTopic;

    @Value("${kafka.topics.user-deactivated}")
    private String userDeactivatedTopic;

    @Value("${kafka.topics.user-activated}")
    private String userActivatedTopic;

    @Value("${kafka.topics.user-deleted}")
    private String userDeletedTopic;

    public void publishUserUpdated(UserUpdatedEvent event) {
        log.info("Publishing UserUpdatedEvent for user: {}", event.getId());
        kafkaTemplate.send(userUpdatedTopic, event.getId(), event);
    }

    public void publishUserDeactivated(UserDeactivatedEvent event) {
        log.info("Publishing UserDeactivatedEvent for user: {}", event.getId());
        kafkaTemplate.send(userDeactivatedTopic, event.getId(), event);
    }

    public void publishUserActivated(UserActivatedEvent event) {
        log.info("Publishing UserActivatedEvent for user: {}", event.getId());
        kafkaTemplate.send(userActivatedTopic, event.getId(), event);
    }

    public void publishUserDeleted(UserDeletedEvent event) {
        log.info("Publishing UserDeletedEvent for user: {}", event.getId());
        kafkaTemplate.send(userDeletedTopic, event.getId(), event);
    }
}
package com.sammy.userservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${kafka.topics.user-registered}")
    private String userRegisteredTopic;

    @Value("${kafka.topics.user-updated}")
    private String userUpdatedTopic;

    @Value("${kafka.topics.user-deactivated}")
    private String userDeactivatedTopic;

    @Value("${kafka.topics.user-activated}")
    private String userActivatedTopic;

    @Value("${kafka.topics.user-deleted}")
    private String userDeletedTopic;

    @Bean
    public NewTopic userRegisteredTopic() {
        return TopicBuilder.name(userRegisteredTopic).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic userUpdatedTopic() {
        return TopicBuilder.name(userUpdatedTopic).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic userDeactivatedTopic() {
        return TopicBuilder.name(userDeactivatedTopic).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic userActivatedTopic() {
        return TopicBuilder.name(userActivatedTopic).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic userDeletedTopic() {
        return TopicBuilder.name(userDeletedTopic).partitions(1).replicas(1).build();
    }
}
package com.greenway.greenway.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue rideRequestsQueue() {
        return new Queue("ride-requests", true);
    }
}

package com.greenway.greenway.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestProducer {

    private final RabbitTemplate rabbitTemplate;

    public void send(RideRequestMessage msg) {
        rabbitTemplate.convertAndSend("ride-requests", msg);
    }

}

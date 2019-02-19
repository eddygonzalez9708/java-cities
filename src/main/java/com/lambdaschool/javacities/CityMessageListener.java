package com.lambdaschool.javacities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CityMessageListener {
    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_SECRET)
    public void receiveSecretMessage(CityMessage rm) {
        log.info("Received Secret Message: {} ", rm.toString());
    }

    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_CITIES1)
    public void receiveCities1Message(CityMessage rm) {
        log.info("Received Cities 1 Message: {}", rm.toString());
    }

    @RabbitListener(queues = JavaCitiesApplication.QUEUE_NAME_CITIES2)
    public void receiveCities2Message(CityMessage rm) {
        log.info("Received Cities 2 Message: {}", rm.toString());
    }
}

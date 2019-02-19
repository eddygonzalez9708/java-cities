package com.lambdaschool.javacities;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Random;

@Slf4j
@RestController
public class CityController {
    private final CityRepository cityrepos;
    private final RabbitTemplate rt;

    public CityController(CityRepository cityrepos, RabbitTemplate rt) {
        this.cityrepos = cityrepos;
        this.rt = rt;
    }

    // /cities/afford ->

    // put all secret messages on the secret queue
    // put all NON secret messages with affordability index < 6 in the cities1 queue
    // put all other messages in the cities2 queue

    @GetMapping("/cities/afford")
    public void findAffordableCities() {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities) {
            int randPrior = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), randPrior, secret);

            if (secret) {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getAffordability() < 6) {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITIES1, message);
            } else {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITIES2, message);
            }
        }
    }

    // /cities/homes ->

    // put all secret messages on the secret queue
    // put all NON secret messages with home prices > 200000 in the cities1 queue
    // put all other messages in the cities2 queue

    @GetMapping("/cities/homes")
    public void findHomePrices() {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities) {
            int randPrior = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), randPrior, secret);

            if (secret) {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            } else if (c.getPrice() > 200000) {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITIES1, message);
            } else {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITIES2, message);
            }
        }
    }

    // /cities/names ->

    // put all secret messages on the secret queue
    // put all NON secret messages in the cities1 queue
    // put nothing in the cities2 queue

    @GetMapping("/cities/names")
    public void findCityNames() {
        ArrayList<City> cities = new ArrayList<>();
        cities.addAll(cityrepos.findAll());

        for (City c : cities) {
            int randPrior = new Random().nextInt(10);
            boolean secret = new Random().nextBoolean();
            final CityMessage message = new CityMessage(c.toString(), randPrior, secret);

            if (secret) {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_SECRET, message);
            } else {
                rt.convertAndSend(JavaCitiesApplication.QUEUE_NAME_CITIES1, message);
            }
        }
    }
}

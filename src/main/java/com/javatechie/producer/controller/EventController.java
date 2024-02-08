package com.javatechie.producer.controller;

import com.javatechie.producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {
    @Autowired
    private KafkaMessagePublisher publisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<String> publishMessage(@PathVariable String message){
        System.out.println("i am in publishMessage");
        try {
            publisher.sendMessageToTopic(message);
            return new ResponseEntity<>("message published successfully", HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

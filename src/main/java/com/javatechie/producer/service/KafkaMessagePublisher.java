package com.javatechie.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {
@Autowired
    private KafkaTemplate<String, Object> template;

  //  private static final String KAFKA_TOPIC="infra-topic";
    private static final String KAFKA_TOPIC="topic3";


    public void sendMessageToTopic(String message){
     //   CompletableFuture<SendResult<String, String>> future = template.send(KAFKA_TOPIC,1,null, message);
        for(int i=0;i<10000;i++) {
            CompletableFuture<SendResult<String, Object>> future = template.send(KAFKA_TOPIC, message +" "+i);

            future.whenComplete((result, ex) -> {
                System.out.println(result.getRecordMetadata().partition());
                if (ex == null) {
                    System.out.println("Sent Message=" + message + "with offset=" +
                            result.getRecordMetadata().offset());
                } else {
                    System.out.println("unable to send message " + message + "due to:"
                            + ex.getMessage());
                }
            });
        }
    }
}

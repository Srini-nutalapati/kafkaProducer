package com.javatechie.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class kafkaProducerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("topic3",3, (short) 1);
    }
}

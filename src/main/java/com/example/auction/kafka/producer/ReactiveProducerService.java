package com.example.auction.kafka.producer;

import com.example.auction.kafka.dto.UpdateAccountCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReactiveProducerService {

    private final ReactiveKafkaProducerTemplate<String, UpdateAccountCommand> reactiveKafkaProducerTemplate;

    @Value("${spring.kafka.topics.answer}")
    private String topic;

    public ReactiveProducerService(ReactiveKafkaProducerTemplate<String, UpdateAccountCommand> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    public void send(UpdateAccountCommand message) {
        reactiveKafkaProducerTemplate.send(topic, message).subscribe();
    }

}

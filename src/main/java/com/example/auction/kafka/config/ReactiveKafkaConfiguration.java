package com.example.auction.kafka.config;

import com.example.auction.kafka.dto.AccountCreatedEvent;
import com.example.auction.kafka.dto.UpdateAccountCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.Map;

@Configuration
public class ReactiveKafkaConfiguration {

    @Bean
    public ReactiveKafkaProducerTemplate<String, UpdateAccountCommand> reactiveKafkaProducerTemplate(
            KafkaProperties properties) {
        Map<String, Object> props = properties.buildProducerProperties();
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(props));
    }

    @Bean
    public ReceiverOptions<String, AccountCreatedEvent> kafkaReceiverOptions(@Value("${spring.kafka.topics.bid}") String topic, KafkaProperties kafkaProperties) {
        ReceiverOptions<String, AccountCreatedEvent> basicReceiverOptions = ReceiverOptions.create(kafkaProperties.buildConsumerProperties());
        return basicReceiverOptions.subscription(Collections.singletonList(topic));
    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, AccountCreatedEvent> reactiveKafkaConsumerTemplate(ReceiverOptions<String, AccountCreatedEvent> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<>(kafkaReceiverOptions);
    }

}

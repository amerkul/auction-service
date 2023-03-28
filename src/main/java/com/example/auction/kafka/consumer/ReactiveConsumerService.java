package com.example.auction.kafka.consumer;


import com.example.auction.domain.Auction;
import com.example.auction.kafka.dto.AccountCreatedEvent;
import com.example.auction.kafka.dto.BidStatus;
import com.example.auction.kafka.dto.UpdateAccountCommand;
import com.example.auction.kafka.producer.ReactiveProducerService;
import com.example.auction.redis.RedisService;
import com.example.auction.service.AuctionService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class ReactiveConsumerService {

    private final ReactiveKafkaConsumerTemplate<String, AccountCreatedEvent> consumerTemplate;
    private final ReactiveProducerService producerService;
    private RedisService redisService;
    private final AuctionService service;

    @PostConstruct
    public void init() {
        consumerTemplate.receive()
                        .subscribe(record -> {
                            redisService.set(
                                    record.value().getId().toString(), record.value().getId().toString(), record.value()
                                        )
                                        .subscribe();
                            process(record.value());
                            record.receiverOffset()
                                      .acknowledge();
                        });
    }

    public void process(AccountCreatedEvent event) {
        Mono<Auction> mono = service.retrieveById(event.getAuctionId());
        mono.doOnNext(auction -> {
            UpdateAccountCommand command = new UpdateAccountCommand();
            command.setId(event.getId());
            command.setName(event.getName());
            command.setBalance(event.getBalance());
            command.setReserved(event.getReserved());
            command.setAuctionId(event.getAuctionId());
            if (event.getReserved() > auction.getStartPrice() && event.getReserved() > auction.getAmount()) {
                auction.setBidder(event.getName());
                auction.setAmount(event.getReserved());
                log.debug("Auction" + auction);
                service.save(auction).subscribe();
                command.setStatus(BidStatus.ACCEPTED);
            } else {
                command.setStatus(BidStatus.REJECTED);
            }
            producerService.send(command);
        }).subscribe();
    }

}

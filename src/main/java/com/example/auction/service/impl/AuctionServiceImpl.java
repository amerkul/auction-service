package com.example.auction.service.impl;


import com.example.auction.domain.Auction;
import com.example.auction.repository.AuctionRepository;
import com.example.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository repository;

    @Override
    public Mono<Auction> save(Auction newAuction) {
        log.info("Create: {}", newAuction);
        return repository.save(newAuction);
    }

    @Override
    public Mono<Void> delete(String auctionId) {
        return repository.deleteById(auctionId);
    }

    @Override
    public Flux<Auction> retrieveAll() {
        return repository.findAll();
    }

}

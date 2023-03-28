package com.example.auction.service;

import com.example.auction.domain.Auction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuctionService {

    Mono<Auction> save(Auction newAuction);
    Mono<Void> delete(String auctiontId);
    Flux<Auction> retrieveAll();
    Mono<Auction> retrieveById(String auctionId);
    Mono<Auction> update(Auction updateAuction);

}

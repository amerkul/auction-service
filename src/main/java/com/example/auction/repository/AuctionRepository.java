package com.example.auction.repository;

import com.example.auction.domain.Auction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuctionRepository extends ReactiveMongoRepository<Auction, String> {

}

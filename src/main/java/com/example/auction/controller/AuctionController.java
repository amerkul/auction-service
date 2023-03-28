package com.example.auction.controller;

import com.example.auction.domain.Auction;
import com.example.auction.dto.AuctionDto;
import com.example.auction.dto.CreateAuctionDto;
import com.example.auction.service.AuctionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService service;
    private final ModelMapper mapper;

    @PostMapping( "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Auction> create(@RequestBody CreateAuctionDto dto) {
        return service.save(mapper.map(dto, Auction.class));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return service.delete(id);
    }

    @GetMapping("/all")
    public Flux<AuctionDto> list() {
        Flux<Auction> auctions = service.retrieveAll();
        return auctions.map(auction -> mapper.map(auction, AuctionDto.class));
    }

}

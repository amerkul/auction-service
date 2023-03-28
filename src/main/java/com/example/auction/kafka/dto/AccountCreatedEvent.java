package com.example.auction.kafka.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountCreatedEvent {

    private Long id;
    private String name;
    private Integer balance;
    private Integer reserved;
    private BidStatus status;
    @JsonProperty("auction_id")
    private String auctionId;

}

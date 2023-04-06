package com.example.auction.kafka.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Data
@ToString
@RedisHash
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreatedEvent {

    private Long id;
    private String name;
    private Integer balance;
    private Integer reserved;
    private BidStatus status;
    @JsonProperty("auction_id")
    private String auctionId;

}

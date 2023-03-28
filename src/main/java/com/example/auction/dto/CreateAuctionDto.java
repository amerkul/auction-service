package com.example.auction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateAuctionDto {

    private String item;
    @JsonProperty("start_price")
    private Integer startPrice;
    private Integer amount;
    private String bidder;

}

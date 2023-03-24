package com.example.auction.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "auction")
public class Auction {

    @Id
    private String id;
    private String item;
    @Field("start_price")
    private Integer startPrice;
    private Integer amount;
    private String bidder;

}

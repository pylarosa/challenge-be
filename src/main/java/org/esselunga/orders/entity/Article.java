package org.esselunga.orders.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Article {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Depot depotRef;
}

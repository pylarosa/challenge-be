package org.esselunga.orders.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class Order {
    private String id;
    private List<Article> articles;
    private Depot depot;
    private Status status;
    private Date orderDate;
    private String courier;
}

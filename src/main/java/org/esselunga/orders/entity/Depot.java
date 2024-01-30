package org.esselunga.orders.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Depot {
    private String id;
    private String nome;
    private Coordinate coordinate;
}

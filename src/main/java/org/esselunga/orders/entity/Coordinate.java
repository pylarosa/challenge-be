package org.esselunga.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Coordinate {
    private Double longitude;
    private Double latitude;
}

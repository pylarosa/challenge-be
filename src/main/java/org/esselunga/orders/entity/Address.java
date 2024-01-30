package org.esselunga.orders.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String country;
    private String city;
    private String province;
    private String cap;
    private String additionalInfo;
    private String recipient;
    private Coordinate coordinate;
}

package org.esselunga.utils.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String city;
    private String province;
    private String cap;
    private String street;
    private String additionalInfo;
    private String recipient;
    private Coordinate coordinates;
}

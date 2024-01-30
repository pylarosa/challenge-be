package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepotDTO {
    private String depotName;
    private Double latitude;
    private Double longitude;
}

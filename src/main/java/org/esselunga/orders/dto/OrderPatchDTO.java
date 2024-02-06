package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;
import org.esselunga.utils.model.Address;

@Builder
@Data
public class OrderPatchDTO {
    private String orderId;
    private Address address;
    private Boolean updated;
}

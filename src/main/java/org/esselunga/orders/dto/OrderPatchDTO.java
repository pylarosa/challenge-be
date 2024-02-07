package org.esselunga.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.esselunga.utils.model.Address;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPatchDTO {
    private String orderId;
    private Address address;
    private Boolean updated;
    private String status;
}

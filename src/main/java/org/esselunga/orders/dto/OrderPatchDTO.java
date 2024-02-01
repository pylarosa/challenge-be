package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;
import org.esselunga.utils.model.Address;
import org.esselunga.utils.model.Status;

@Builder
@Data
public class OrderPatchDTO {
    private Status status;
    private Address newAddress;
}

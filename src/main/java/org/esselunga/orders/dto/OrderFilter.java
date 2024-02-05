package org.esselunga.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.esselunga.utils.model.Status;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilter {
    private Status status;
    private String customer;
    private String dateBy;
    private String dateTo;
}

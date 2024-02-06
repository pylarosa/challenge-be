package org.esselunga.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.esselunga.utils.model.Status;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFilter {
    private Status status;
    private String customer;
    private Date dateBy;
    private Date dateTo;
}

package org.esselunga.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.utils.model.Address;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String orderId;
    private List<ProductDTO> productsDto;
    private String status;
    private Date orderDate;
    private Date updateDate;
    private Address address;
    private String customer;
    private Boolean updated;
}

package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.utils.model.Address;
import org.esselunga.utils.model.Status;

import java.util.Date;
import java.util.List;

@Builder
@Data
public class OrderDTO {
    private String orderId;
    private List<ProductDTO> productsDto;
    private DepotDTO depot;
    private Status status;
    private Date orderDate;
    private Date updateDate;
    private Address address;
    private String customer;
    private Boolean updated;
}

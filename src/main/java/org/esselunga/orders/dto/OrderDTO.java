package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;
import org.esselunga.orders.entity.Status;

import java.util.List;

@Builder
@Data
public class OrderDTO {
    private String id;
    private List<ArticleDTO> articleDTOList;
    private DepotDTO depotDTO;
    private Status status;
    private String orderDate;
    private String courier;
}

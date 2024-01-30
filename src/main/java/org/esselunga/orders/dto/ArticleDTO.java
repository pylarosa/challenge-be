package org.esselunga.orders.dto;

import lombok.Builder;
import lombok.Data;
import org.esselunga.orders.entity.Depot;

@Builder
@Data
public class ArticleDTO {
    private String id;
    private String name;
    private Integer quantity;
    private Double price;
    private Depot depotRef;
}

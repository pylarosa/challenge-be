package org.esselunga.utils.order;

import jakarta.enterprise.inject.Model;
import org.esselunga.orders.dto.OrderDTO;

@Model
public class OrderUtils {
    public double calculateTotalPrice(OrderDTO orderDTO) {
        return orderDTO.getProductsDto().stream()
                .mapToDouble(productDTO -> productDTO.getQuantity() * productDTO.getPrice())
                .sum();
    }
}

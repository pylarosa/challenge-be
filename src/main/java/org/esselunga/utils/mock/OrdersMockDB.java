package org.esselunga.utils.mock;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Data;
import org.esselunga.orders.entity.Order;
import org.esselunga.orders.entity.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Data
public class OrdersMockDB {
    private List<Order> persistedOrders = new ArrayList<>();

    public void placeOrder(Order order) {
        order.setId(UUID.randomUUID().toString());
        order.setStatus(Status.PRESO_IN_CARICO);
        this.persistedOrders.add(order);

    }
}

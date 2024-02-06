package org.esselunga.orders.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.entity.Order;

import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order> {

    public List<Order> getFilteredOrders(OrderFilter orderFilter) {
        if (orderFilter.getDateBy() != null || orderFilter.getDateTo() != null || orderFilter.getStatus() != null || orderFilter.getCustomer() != null) {
            return this.findAll().stream()
                    .filter(order -> orderFilter.getStatus() == null || order.getStatus().name().equals(orderFilter.getStatus().name()))
                    .filter(order -> orderFilter.getCustomer() == null || order.getCustomer().equals(orderFilter.getCustomer()))
                    .filter(order -> orderFilter.getDateBy() == null || order.getOrderDate().compareTo(orderFilter.getDateBy()) >= 0)
                    .filter(order -> orderFilter.getDateTo() == null || order.getOrderDate().compareTo(orderFilter.getDateTo()) <= 0)
                    .toList();
        }
        return this.findAll().stream().toList();
    }
}

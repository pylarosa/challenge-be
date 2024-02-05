package org.esselunga.orders.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.entity.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class OrderRepository implements PanacheMongoRepository<Order> {

    public List<Order> getFilteredOrders(OrderFilter orderFilter) throws ParseException {
        if (orderFilter != null) {
            Date dateBy = orderFilter.getDateBy() != null ? new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(orderFilter.getDateBy()) : null;
            Date dateTo = orderFilter.getDateTo() != null ? new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(orderFilter.getDateTo()) : null;
            return this.findAll().stream()
                    .filter(order -> orderFilter.getStatus() == null || order.getStatus().name().equals(orderFilter.getStatus().name()))
                    .filter(order -> orderFilter.getCustomer() == null || order.getCustomer().equals(orderFilter.getCustomer()))
                    .filter(order -> orderFilter.getDateBy() == null || order.getOrderDate().compareTo(dateBy) >= 0)
                    .filter(order -> orderFilter.getDateTo() == null || order.getOrderDate().compareTo(dateTo) <= 0)
                    .toList();
        }
        return this.findAll().stream().toList();
    }
}

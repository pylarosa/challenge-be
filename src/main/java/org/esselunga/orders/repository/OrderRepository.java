package org.esselunga.orders.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.esselunga.exception.RepositoryException;
import org.esselunga.orders.entity.Order;
import org.esselunga.utils.Constants;
import org.esselunga.utils.mock.OrdersMockDB;

@ApplicationScoped
public class OrderRepository {

    @Inject
    OrdersMockDB mockDB;

    public String insertOrderMock(Order order) throws RepositoryException {
        try {
            mockDB.placeOrder(order);
            return Constants.ORDINE_PRESO_IN_CARICO;

        } catch (Exception ex) {
            throw new RepositoryException("OrderRepository.insertOrderMock error: " + ex.getMessage());
        }
    }
}

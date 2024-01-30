package org.esselunga.orders.service;

import org.esselunga.exception.ServiceException;
import org.esselunga.orders.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    String insertOrder(OrderDTO orderDTO) throws ServiceException;

    List<OrderDTO> getAllOrders() throws ServiceException;
}


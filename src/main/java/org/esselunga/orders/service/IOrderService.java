package org.esselunga.orders.service;

import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderPatchDTO;
import org.esselunga.utils.exception.ServiceException;

import java.util.List;

public interface IOrderService {
    String insertOrder(OrderDTO orderDTO) throws ServiceException;

    List<OrderDTO> getAllOrders() throws ServiceException;

    String updateOrder(String idOrder, OrderPatchDTO orderPatch) throws ServiceException;
}


package org.esselunga.orders.service;

import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.dto.OrderPatchDTO;
import org.esselunga.utils.exception.ServiceException;

import java.util.List;

public interface IOrderService {
    OrderDTO insertOrder(OrderDTO orderDTO) throws ServiceException;

    List<OrderDTO> getAllOrders() throws ServiceException;

    OrderDTO updateOrder(OrderPatchDTO orderPatch) throws ServiceException;

    List<OrderDTO> getFilteredOrders(OrderFilter orderFilter) throws ServiceException;
}


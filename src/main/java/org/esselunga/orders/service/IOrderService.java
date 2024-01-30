package org.esselunga.orders.service;

import org.esselunga.exception.ServiceException;
import org.esselunga.orders.dto.OrderDTO;

import java.text.ParseException;

public interface IOrderService {
    String insertOrder(OrderDTO orderDTO) throws ServiceException;

/*    List<OrderDTO> getAllOrders() throws ServiceException;

    List<OrderDTO> getFilteredOrders(OrderFilter orderFilter) throws ServiceException;

    String updateOrder(String idOrder, OrderPatch orderPatch) throws ServiceException;

    ResponsePath calculatePath(DepotDTO depotDTO) throws ServiceException;*/
}


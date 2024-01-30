package org.esselunga.orders.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.esselunga.exception.MapperException;
import org.esselunga.exception.RepositoryException;
import org.esselunga.exception.ServiceException;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.orders.mapper.OrderMapper;
import org.esselunga.orders.repository.OrderRepository;

import java.util.List;

@Model
public class OrderServiceImpl implements IOrderService {
    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderRepository orderRepository;

    @Override
    public String insertOrder(OrderDTO orderDTO) throws ServiceException {
        try {
            Order order = orderMapper.convertDtoToEntity(orderDTO);
            return orderRepository.insertOrderMock(order);

        } catch (MapperException | RepositoryException ex) {
            throw new ServiceException("OrderServiceImpl.insertOrder error:" + ex.getMessage());
        }
    }

    public List<OrderDTO> getAllOrders() throws ServiceException {
        try {
            List<Order> orders = orderRepository.getAllOrders();
            return orderMapper.convertEntityToDto(orders);

        } catch (MapperException ex) {
            throw new ServiceException("OrderServiceImpl.getOrder error:" + ex.getMessage());
        }
    }
}

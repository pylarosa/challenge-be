package org.esselunga.orders.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.dto.OrderPatchDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.orders.mapper.OrderMapper;
import org.esselunga.orders.repository.OrderRepository;
import org.esselunga.utils.Constants;
import org.esselunga.utils.exception.ServiceException;
import org.esselunga.utils.model.Address;
import org.esselunga.utils.model.Status;

import java.util.List;

@Model
public class OrderServiceImpl implements IOrderService {
    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderRepository orderRepository;

    @Override
    public OrderDTO insertOrder(OrderDTO orderDTO) throws ServiceException {
        try {
            Order order = orderMapper.convertDtoToEntity(orderDTO);
            orderRepository.persist(order);
            return orderMapper.convertEntityToDto(order);

        } catch (Exception ex) {
            throw new ServiceException("OrderServiceImpl.insertOrder error: " + ex.getMessage());
        }
    }

    @Override
    public List<OrderDTO> getAllOrders() throws ServiceException {
        try {
            List<Order> orders = orderRepository.listAll();
            return orderMapper.convertEntityToDto(orders);

        } catch (Exception ex) {
            throw new ServiceException("OrderServiceImpl.getAllOrders error: " + ex.getMessage());
        }
    }

    @Override
    public String updateOrder(String idOrder, OrderPatchDTO orderPatch) throws ServiceException {
        try {
            Order order = orderRepository.findById(new ObjectId(idOrder));
            if (orderPatch == null) {
                return "Nessuna modifica richiesta";
            }

            Status newStatus = orderPatch.getStatus() != null ? orderPatch.getStatus() : order.getStatus();
            Address newAddress = orderPatch.getNewAddress() != null ? orderPatch.getNewAddress() : order.getAddress();
            Order updatedOrder = Order.builder()
                    .status(newStatus)
                    .address(newAddress)
                    .build();
            orderRepository.update(updatedOrder);
            return Constants.ORDINE_MODIFICATO;

        } catch (Exception ex) {
            throw new ServiceException("OrderServiceImpl.updateOrder error: " + ex.getMessage());
        }
    }


    @Override
    public List<OrderDTO> getFilteredOrders(OrderFilter orderFilter) throws ServiceException {
        try {
            List<Order> filteredOrders = orderRepository.getFilteredOrders(orderFilter);
            return orderMapper.convertEntityToDto(filteredOrders);

        } catch (Exception ex) {
            throw new ServiceException("OrderServiceImpl.getFilteredOrders error:" + ex.getMessage());
        }
    }

    public void deleteAll() throws ServiceException {
        try {
            orderRepository.deleteAll();

        } catch (Exception ex) {
            throw new ServiceException("OrderServiceImpl.deleteAll error:" + ex.getMessage());
        }
    }
}

package org.esselunga.orders.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.utils.AbstractMapperComponent;
import org.esselunga.utils.exception.MapperException;
import org.esselunga.utils.model.Status;

import java.util.Date;

@ApplicationScoped
public class OrderMapper extends AbstractMapperComponent<OrderDTO, Order> {

    @Override
    public OrderDTO convertEntityToDto(Order entity) throws MapperException {
        try {
            if (entity != null) {
                return OrderDTO.builder()
                        .orderId(entity.getId().toString())
                        .productsDto(entity.getProducts())
                        .customer(entity.getCustomer())
                        .address(entity.getAddress())
                        .orderDate(entity.getOrderDate())
                        .updateDate(entity.getUpdateDate())
                        .status(entity.getStatus().getDescrizione())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("OrderMapper.convertEntityToDto error: " + ex.getMessage());
        }
    }

    @Override
    public Order convertDtoToEntity(OrderDTO dto) throws MapperException {
        try {
            if (dto != null) {
                Date updateDate = new Date();
                return Order.builder()
                        .products(dto.getProductsDto())
                        .customer(dto.getCustomer())
                        .address(dto.getAddress())
                        .orderDate(updateDate)
                        .updateDate(dto.getUpdated().equals(true) ? updateDate : null)
                        .status(Status.fromDescrizione(dto.getStatus()))
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("OrderMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

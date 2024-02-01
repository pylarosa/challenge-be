package org.esselunga.orders.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.esselunga.depots.mapper.DepotMapper;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.products.mapper.ProductMapper;
import org.esselunga.utils.AbstractMapperComponent;
import org.esselunga.utils.exception.MapperException;

import java.util.Date;

@ApplicationScoped
public class OrderMapper extends AbstractMapperComponent<OrderDTO, Order> {
    @Inject
    ProductMapper productMapper;

    @Inject
    DepotMapper depotMapper;

    @Override
    public OrderDTO convertEntityToDto(Order entity) throws MapperException {
        try {
            if (entity != null) {
                return OrderDTO.builder()
                        .orderId(entity.getId().toString())
                        .depot(depotMapper.convertEntityToDto(entity.getDepot()))
                        .productsDto(productMapper.convertEntityToDto(entity.getProducts()))
                        .customer(entity.getCustomer())
                        .address(entity.getAddress())
                        .orderDate(entity.getOrderDate())
                        .updateDate(entity.getUpdateDate())
                        .status(entity.getStatus())
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
                        .depot(depotMapper.convertDtoToEntity(dto.getDepot()))
                        .customer(dto.getCustomer())
                        .address(dto.getAddress())
                        .orderDate(updateDate)
                        .updateDate(dto.getUpdated().equals(true) ? updateDate : null)
                        .status(dto.getStatus())
                        .products(productMapper.convertDtoToEntity(dto.getProductsDto()))
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("OrderMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

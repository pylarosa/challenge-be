package org.esselunga.orders.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.esselunga.exception.MapperException;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.utils.AbstractMapperComponent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class OrderMapper extends AbstractMapperComponent<OrderDTO, Order> {
    @Inject
    ArticleMapper articleMapper;

    @Inject
    DepotMapper depotMapper;

    @Override
    public OrderDTO convertEntityToDto(Order entity) throws MapperException {
        try {
            if (entity != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                return OrderDTO.builder()
                        .id(entity.getId())
                        .articleDTOList(articleMapper.convertEntityToDto(entity.getArticles()))
                        .depotDTO(depotMapper.convertEntityToDto(entity.getDepot()))
                        .status(entity.getStatus())
                        .orderDate(dateFormat.format(entity.getOrderDate()))
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
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dto.getOrderDate());
                return Order.builder()
                        .articles(articleMapper.convertDtoToEntity(dto.getArticleDTOList()))
                        .depot(depotMapper.convertDtoToEntity(dto.getDepotDTO()))
                        .status(dto.getStatus())
                        .orderDate(orderDate)
                        .courier(dto.getCourier())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("OrderMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

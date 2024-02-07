package org.esselunga.orders.service;

import org.bson.types.ObjectId;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.entity.Order;
import org.esselunga.orders.mapper.OrderMapper;
import org.esselunga.orders.repository.OrderRepository;
import org.esselunga.utils.exception.MapperException;
import org.esselunga.utils.exception.ServiceException;
import org.esselunga.utils.order.OrderUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {
    private static final ObjectId ID = new ObjectId(new Timestamp(1L));
    private static final String STRING = "string";

    @InjectMocks
    OrderServiceImpl service;

    @Mock
    OrderMapper orderMapper;

    @Mock
    OrderRepository repository;

    @Mock
    OrderUtils orderUtils;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getFilteredOrdersTest() throws MapperException, ServiceException {
        List<Order> list = new ArrayList<>();
        List<OrderDTO> listDTO = new ArrayList<>();
        when(repository.getFilteredOrders(any(OrderFilter.class))).thenReturn(list);
        when(orderMapper.convertEntityToDto(list)).thenReturn(listDTO);

        assertNotNull(service.getFilteredOrders(new OrderFilter()));

        when(repository.getFilteredOrders(any(OrderFilter.class))).thenThrow(new RuntimeException(STRING));

        try {
            service.getFilteredOrders(new OrderFilter());
            fail();

        } catch (Exception ex) {
            assertEquals("OrderServiceImpl.getFilteredOrders error: " + STRING, ex.getMessage());
        }
    }
}


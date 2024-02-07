package org.esselunga.orders.mapper;

import org.bson.types.ObjectId;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.entity.Order;
import org.esselunga.products.mapper.ProductMapper;
import org.esselunga.utils.exception.MapperException;
import org.esselunga.utils.model.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

public class OrderMapperTest {
    private static final ObjectId ID = new ObjectId(new Timestamp(1L));
    private static final String STRING = "string";

    @InjectMocks
    OrderMapper orderMapper;

    @Mock
    ProductMapper productMapper;

    @Mock
    OrderDTO dto;

    @Mock
    Order entity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void convertEntityToDtoTest() throws MapperException {
        Order expectedResponse = new Order();
        expectedResponse.setId(ID);
        expectedResponse.setStatus(Status.CONSEGNATO);
        doReturn(new ArrayList<>()).when(productMapper).convertEntityToDto(anyList());
        OrderDTO response = orderMapper.convertEntityToDto(expectedResponse);
        assertEquals(expectedResponse.getId(), new ObjectId(response.getOrderId()));

        assertNull(orderMapper.convertEntityToDto((Order) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(entity).getId();

        try {
            orderMapper.convertEntityToDto(entity);
            fail();

        } catch (Exception ex) {
            assertEquals("OrderMapper.convertEntityToDto error: " + STRING, ex.getMessage());
        }
    }

    @Test
    public void convertDtoToEntityTest() throws MapperException {
        OrderDTO expectedResponse = new OrderDTO();
        expectedResponse.setOrderId(String.valueOf(ID));
        expectedResponse.setTotal(1d);
        expectedResponse.setUpdateDate(new Date());
        expectedResponse.setStatus("CONSEGNATO");
        expectedResponse.setUpdated(true);
        doReturn(new ArrayList<>()).when(productMapper).convertDtoToEntity(anyList());
        Order response = orderMapper.convertDtoToEntity(expectedResponse);
        assertEquals(expectedResponse.getTotal(), response.getTotal());

        assertNull(orderMapper.convertDtoToEntity((OrderDTO) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(dto).getTotal();

        try {
            orderMapper.convertDtoToEntity(dto);
            fail();

        } catch (Exception ex) {
            assertEquals("OrderMapper.convertDtoToEntity error: " + "No enum constant with descrizione: null", ex.getMessage());
        }
    }
}

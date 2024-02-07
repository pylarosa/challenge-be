package org.esselunga.orders.api;


import jakarta.ws.rs.core.Response;
import org.esselunga.orders.dto.OrderDTO;
import org.esselunga.orders.dto.OrderFilter;
import org.esselunga.orders.dto.OrderPatchDTO;
import org.esselunga.orders.service.OrderServiceImpl;
import org.esselunga.utils.exception.ApplicationException;
import org.esselunga.utils.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrdersControllerTest {
    private static final String STRING = "string";

    @InjectMocks
    private OrderController controller;

    @Mock
    private OrderServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrdersTest() throws ServiceException, ApplicationException {
        List<OrderDTO> orders = new ArrayList<>();
        when(service.getAllOrders()).thenReturn(orders);
        Response response = controller.getAllOrders();
        assertTrue(response.hasEntity());

        // Exception Branch
        when(service.getAllOrders()).thenThrow(new RuntimeException(STRING));

        try {
            controller.getAllOrders();
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void insertOrderTest() throws ServiceException, ApplicationException {
        OrderDTO expectedResponse = new OrderDTO();
        when(service.insertOrder(any(OrderDTO.class))).thenReturn(expectedResponse);
        Response actualResponse = controller.insertOrder(expectedResponse);
        assertEquals(expectedResponse, actualResponse.getEntity());

        // Exception Branch
        when(service.insertOrder(any(OrderDTO.class))).thenThrow(new RuntimeException(STRING));

        try {
            controller.insertOrder(expectedResponse);
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void updateOrderTest() throws ApplicationException, ServiceException {
        doNothing().when(service).deleteAll();
        OrderPatchDTO orderPatchDTO = new OrderPatchDTO();
        assertEquals(200, controller.updateOrder(orderPatchDTO).getStatus());

        // Exception Branch
        doThrow(new RuntimeException(STRING)).when(service).updateOrder(any(OrderPatchDTO.class));

        try {
            controller.updateOrder(orderPatchDTO);
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void getFilteredOrdersTest() throws ApplicationException, ServiceException {
        List<OrderDTO> expectedResponse = new ArrayList<>();
        OrderFilter orderFilter = new OrderFilter();
        doReturn(expectedResponse).when(service).getFilteredOrders(orderFilter);
        assertEquals(200, controller.getFilteredOrders(any(OrderFilter.class)).getStatus());

        // Exception Branch
        doThrow(new RuntimeException(STRING)).when(service).getFilteredOrders(orderFilter);

        try {
            controller.getFilteredOrders(orderFilter);
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void deleteAllTest() throws ApplicationException, ServiceException {
        doNothing().when(service).deleteAll();
        assertEquals(200, controller.deleteAll().getStatus());

        // Exception Branch
        doThrow(new RuntimeException(STRING)).when(service).deleteAll();

        try {
            controller.deleteAll();
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }
}

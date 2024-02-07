package org.esselunga.product.api;

import jakarta.ws.rs.core.Response;
import org.esselunga.products.api.ProductController;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.products.service.ProductServiceImpl;
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

public class ProductControllerTest {
    private static final String STRING = "string";

    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProductsTest() throws ServiceException, ApplicationException {
        List<ProductDTO> products = new ArrayList<>();
        when(service.getAllProducts()).thenReturn(products);
        Response response = controller.getAllProducts();
        assertTrue(response.hasEntity());

        // Exception Branch
        when(service.getAllProducts()).thenThrow(new RuntimeException(STRING));

        try {
            controller.getAllProducts();
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void insertProductTest() throws ServiceException, ApplicationException {
        ProductDTO expectedResponse = new ProductDTO();
        when(service.insertProduct(any(ProductDTO.class))).thenReturn(STRING);
        Response actualResponse = controller.insertProduct(expectedResponse);
        assertEquals(STRING, actualResponse.getEntity());

        // Exception Branch
        when(service.insertProduct(any(ProductDTO.class))).thenThrow(new RuntimeException(STRING));

        try {
            controller.insertProduct(expectedResponse);
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void getProductByOrderId() throws ApplicationException, ServiceException {
        List<ProductDTO> list = new ArrayList<>();
        doReturn(list).when(service).getProductById(STRING);
        assertEquals(200, controller.insertProduct(any(ProductDTO.class)).getStatus());

        // Exception Branch
        doThrow(new RuntimeException(STRING)).when(service).getProductById(STRING);

        try {
            controller.getProductByOrderId(STRING);
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

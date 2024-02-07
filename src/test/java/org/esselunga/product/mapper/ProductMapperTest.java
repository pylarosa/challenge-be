package org.esselunga.product.mapper;

import org.bson.types.ObjectId;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.products.entity.Product;
import org.esselunga.products.mapper.ProductMapper;
import org.esselunga.utils.exception.MapperException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class ProductMapperTest {
    private static final ObjectId ID = new ObjectId(new Timestamp(1L));
    private static final String STRING = "string";

    @InjectMocks
    ProductMapper productMapper;

    @Mock
    ProductDTO dto;

    @Mock
    Product entity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void convertEntityToDtoTest() throws MapperException {
        Product expectedResponse = new Product();
        expectedResponse.setId(ID);
        expectedResponse.setQuantity(1);
        ProductDTO response = productMapper.convertEntityToDto(expectedResponse);
        assertEquals(expectedResponse.getQuantity(), response.getQuantity());

        assertNull(productMapper.convertEntityToDto((Product) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(entity).getId();

        try {
            productMapper.convertEntityToDto(entity);
            fail();

        } catch (Exception ex) {
            assertEquals("ProductMapper.convertEntityToDto error: " + STRING, ex.getMessage());
        }
    }

    @Test
    public void convertDtoToEntityTest() throws MapperException {
        ProductDTO expectedResponse = new ProductDTO();
        expectedResponse.setProductId(String.valueOf(ID));
        Product response = productMapper.convertDtoToEntity(dto);
        assertEquals(expectedResponse.getName(), response.getName());

        assertNull(productMapper.convertDtoToEntity((ProductDTO) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(dto).getProductId();

        try {
            productMapper.convertDtoToEntity(dto);
            fail();

        } catch (Exception ex) {
            assertEquals("ProductMapper.convertDtoToEntity error: " + STRING,  ex.getMessage());
        }
    }
}

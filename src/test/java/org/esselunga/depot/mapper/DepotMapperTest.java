package org.esselunga.depot.mapper;

import org.bson.types.ObjectId;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.entity.Depot;
import org.esselunga.depots.mapper.DepotMapper;
import org.esselunga.utils.exception.MapperException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

public class DepotMapperTest {
    private static final ObjectId ID = new ObjectId(new Timestamp(1L));
    private static final String STRING = "string";

    @InjectMocks
    DepotMapper depotMapper;

    @Mock
    DepotDTO dto;

    @Mock
    Depot entity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void convertEntityToDtoTest() throws MapperException {
        Depot expectedResponse = new Depot();
        expectedResponse.setId(ID);
        DepotDTO response = depotMapper.convertEntityToDto(expectedResponse);
        assertEquals(expectedResponse.getId(), new ObjectId(response.getDepotId()));

        assertNull(depotMapper.convertEntityToDto((Depot) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(entity).getId();

        try {
            depotMapper.convertEntityToDto(entity);
            fail();

        } catch (Exception ex) {
            assertEquals("DepotMapper.convertEntityToDto error: " + STRING, ex.getMessage());
        }
    }

    @Test
    public void convertDtoToEntityTest() throws MapperException {
        DepotDTO expectedResponse = new DepotDTO();
        expectedResponse.setDepotId(String.valueOf(ID));
        expectedResponse.setName(STRING);
        Depot response = depotMapper.convertDtoToEntity(expectedResponse);
        assertEquals(expectedResponse.getName(), response.getName());

        assertNull(depotMapper.convertDtoToEntity((DepotDTO) null));

        // Exception Branch

        doThrow(new RuntimeException(STRING)).when(dto).getName();

        try {
            depotMapper.convertDtoToEntity(dto);
            fail();

        } catch (Exception ex) {
            assertEquals("DepotMapper.convertDtoToEntity error: " + STRING, ex.getMessage());
        }
    }
}

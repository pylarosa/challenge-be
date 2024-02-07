package org.esselunga.depot.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.entity.Depot;
import org.esselunga.depots.mapper.DepotMapper;
import org.esselunga.depots.repository.DepotRepository;
import org.esselunga.depots.service.DepotServiceImpl;
import org.esselunga.utils.exception.MapperException;
import org.esselunga.utils.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@QuarkusTest
public class DepotServiceImplTest {
    private static final ObjectId ID = new ObjectId(new Timestamp(1L));
    private static final String STRING = "string";

    @InjectMocks
    DepotServiceImpl service;

    @Mock
    DepotMapper depotMapper;

    @InjectMock
    DepotRepository repository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void insertDepotTest() throws MapperException, ServiceException {
        Depot depot = new Depot();
        depot.setId(ID);
        DepotDTO dto = new DepotDTO();
        when(depotMapper.convertDtoToEntity(any(DepotDTO.class))).thenReturn(depot);
        doNothing().when(repository).persist(any(Depot.class));
        assertEquals(depot.getId().toString(), service.insertDepot(dto));

        // Exception Branch
        doThrow(new RuntimeException(STRING)).when(depotMapper).convertDtoToEntity(any(DepotDTO.class));

        try {
            service.insertDepot(dto);
            fail();

        } catch (ServiceException ex) {
            assertEquals("DepotServiceImpl.insertDepot error: " + STRING, ex.getMessage());
        }
    }
}

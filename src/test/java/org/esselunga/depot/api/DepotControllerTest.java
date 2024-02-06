package org.esselunga.depot.api;

import jakarta.ws.rs.core.Response;
import org.esselunga.depots.api.DepotController;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.service.DepotServiceImpl;
import org.esselunga.utils.exception.ApplicationException;
import org.esselunga.utils.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class DepotControllerTest {
    private static final String STRING = "string";

    @InjectMocks
    private DepotController controller;

    @Mock
    private DepotServiceImpl service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void insertDepotTest() throws ServiceException, ApplicationException {
        when(service.insertDepot(any(DepotDTO.class))).thenReturn(STRING);
        Response response = controller.insertDepot(new DepotDTO());
        assertTrue(response.hasEntity());

        // Exception Branch
        when(service.insertDepot(any(DepotDTO.class))).thenThrow(new ServiceException(STRING));

        try {
            controller.insertDepot((new DepotDTO()));
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void getDepotByIdTest() throws ServiceException, ApplicationException {
        DepotDTO expectedResponse = new DepotDTO();
        when(service.getDepotById(anyString())).thenReturn(expectedResponse);
        Response actualResponse = controller.getDepotById(STRING);
        assertEquals(expectedResponse, actualResponse.getEntity());

        // Exception Branch
        when(service.getDepotById(anyString())).thenThrow(new ServiceException(STRING));

        try {
            controller.getDepotById(STRING);
            fail();

        } catch (Exception ex) {
            assertEquals(STRING, ex.getMessage());
        }
    }

    @Test
    void deleteAllTest() throws ApplicationException, ServiceException {
        doNothing().when(service).deleteAll();
        assertEquals(200, controller.deleteAll().getStatus());
    }
}

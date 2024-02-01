package org.esselunga.depots.service;

import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.utils.exception.ServiceException;

public interface IDepotService {
    String insertDepot(DepotDTO depotDTO) throws ServiceException;

    DepotDTO getDepotById(String depotId) throws ServiceException;

    void deleteAll() throws ServiceException;
}

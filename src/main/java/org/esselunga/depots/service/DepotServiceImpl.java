package org.esselunga.depots.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.entity.Depot;
import org.esselunga.depots.mapper.DepotMapper;
import org.esselunga.depots.repository.DepotRepository;
import org.esselunga.utils.exception.ServiceException;

@Model
public class DepotServiceImpl implements IDepotService {

    @Inject
    DepotRepository depotRepository;

    @Inject
    DepotMapper depotMapper;


    @Override
    public String insertDepot(DepotDTO depotDTO) throws ServiceException {
        try {
            Depot depot = depotMapper.convertDtoToEntity(depotDTO);
            depotRepository.persist(depot);
            return depot.getId().toString();

        } catch (Exception ex) {
            throw new ServiceException("DepotServiceImpl.insertDepot error: " + ex.getMessage());
        }
    }

    @Override
    public DepotDTO getDepotById(String depotId) throws ServiceException {
        try {
            Depot depot = depotRepository.findById(new ObjectId(depotId));
            return depotMapper.convertEntityToDto(depot);

        } catch (Exception ex) {
            throw new ServiceException("DepotServiceImpl.getDepotById error: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            depotRepository.deleteAll();

        } catch (Exception ex) {
            throw new ServiceException("DepotServiceImpl.deleteAll error: " + ex.getMessage());
        }
    }
}

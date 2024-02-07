package org.esselunga.depots.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.entity.Depot;
import org.esselunga.depots.mapper.DepotMapper;
import org.esselunga.depots.repository.DepotRepository;
import org.esselunga.utils.exception.ServiceException;

import java.util.List;

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
    public DepotDTO getDepot() throws ServiceException {
        try {
            List<Depot> depotList = depotRepository.findAll().stream().toList();
            return depotMapper.convertEntityToDto(depotList.getFirst());

        } catch (Exception ex) {
            throw new ServiceException("DepotServiceImpl.getDepot error: " + ex.getMessage());
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

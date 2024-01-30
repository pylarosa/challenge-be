package org.esselunga.orders.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.exception.MapperException;
import org.esselunga.orders.dto.DepotDTO;
import org.esselunga.orders.entity.Coordinate;
import org.esselunga.orders.entity.Depot;
import org.esselunga.utils.AbstractMapperComponent;

@ApplicationScoped
public class DepotMapper extends AbstractMapperComponent<DepotDTO, Depot> {
    @Override
    public DepotDTO convertEntityToDto(Depot entity) throws MapperException {
        try {
            if (entity != null) {
                return DepotDTO.builder()
                        .depotName(entity.getNome())
                        .latitude(entity.getCoordinate().getLatitude())
                        .longitude(entity.getCoordinate().getLatitude())
                        .build();
            }
            return null;
        } catch (Exception ex) {
            throw new MapperException("DepotMapper.convertEntityToDto error: " + ex.getMessage());
        }
    }

    @Override
    public Depot convertDtoToEntity(DepotDTO dto) throws MapperException {
        try {
            if (dto != null) {
                return Depot.builder()
                        .nome(dto.getDepotName())
                        .coordinate(new Coordinate(dto.getLongitude(), dto.getLatitude()))
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("DepotMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

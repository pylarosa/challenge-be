package org.esselunga.depots.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.depots.dto.DepotDTO;
import org.esselunga.depots.entity.Depot;
import org.esselunga.utils.AbstractMapperComponent;
import org.esselunga.utils.exception.MapperException;

@ApplicationScoped
public class DepotMapper extends AbstractMapperComponent<DepotDTO, Depot> {

    @Override
    public DepotDTO convertEntityToDto(Depot entity) throws MapperException {
        try {
            if (entity != null) {
                return DepotDTO.builder()
                        .depotId(entity.getId().toString())
                        .name(entity.getName())
                        .address(entity.getAddress())
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
                        .name(dto.getName())
                        .address(dto.getAddress())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("DepotMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

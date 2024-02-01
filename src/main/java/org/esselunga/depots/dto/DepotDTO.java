package org.esselunga.depots.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.esselunga.utils.model.Address;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepotDTO {
    private String depotId;
    private String name;
    private Address address;
}

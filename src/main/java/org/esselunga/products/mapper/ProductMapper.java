package org.esselunga.products.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.products.entity.Product;
import org.esselunga.utils.AbstractMapperComponent;
import org.esselunga.utils.exception.MapperException;

@ApplicationScoped
public class ProductMapper extends AbstractMapperComponent<ProductDTO, Product> {

    @Override
    public ProductDTO convertEntityToDto(Product entity) throws MapperException {
        try {
            if (entity != null) {
                return ProductDTO.builder()
                        .productId(entity.getId().toString())
                        .name(entity.getName())
                        .quantity(entity.getQuantity())
                        .price(entity.getPrice())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("ProductMapper.convertEntityToDto error: " + ex.getMessage());
        }
    }

    @Override
    public Product convertDtoToEntity(ProductDTO dto) throws MapperException {
        try {
            if (dto != null) {
                return Product.builder()
                        .name(dto.getName())
                        .quantity(dto.getQuantity())
                        .price(dto.getPrice())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("ProductMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

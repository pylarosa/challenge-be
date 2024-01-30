package org.esselunga.orders.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.exception.MapperException;
import org.esselunga.orders.dto.ArticleDTO;
import org.esselunga.orders.entity.Article;
import org.esselunga.utils.AbstractMapperComponent;

@ApplicationScoped
public class ArticleMapper extends AbstractMapperComponent<ArticleDTO, Article> {

    @Override
    public ArticleDTO convertEntityToDto(Article entity) throws MapperException {
        try {
            if (entity != null) {
                return ArticleDTO.builder()
                        .id(entity.getId())
                        .name(entity.getName())
                        .quantity(entity.getQuantity())
                        .depotRef(entity.getDepotRef())
                        .price(entity.getPrice())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("ArticleMapper.convertEntityToDto error: " + ex.getMessage());
        }
    }

    @Override
    public Article convertDtoToEntity(ArticleDTO dto) throws MapperException {
        try {
            if (dto != null) {
                return Article.builder()
                        .name(dto.getName())
                        .quantity(dto.getQuantity())
                        .depotRef(dto.getDepotRef())
                        .price(dto.getPrice())
                        .build();
            }
            return null;

        } catch (Exception ex) {
            throw new MapperException("ArticleMapper.convertDtoToEntity error: " + ex.getMessage());
        }
    }
}

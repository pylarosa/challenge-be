package org.esselunga.products.service;

import org.esselunga.products.dto.ProductDTO;
import org.esselunga.utils.exception.ServiceException;

import java.util.List;

public interface IProductService {
    String insertProduct(ProductDTO productDTO) throws ServiceException;

    List<ProductDTO> getAllProducts() throws ServiceException;

    ProductDTO getProductById(String productId) throws ServiceException;

    void deleteAll() throws ServiceException;
}


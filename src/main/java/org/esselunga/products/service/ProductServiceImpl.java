package org.esselunga.products.service;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import org.bson.types.ObjectId;
import org.esselunga.products.dto.ProductDTO;
import org.esselunga.products.entity.Product;
import org.esselunga.products.mapper.ProductMapper;
import org.esselunga.products.repository.ProductRepository;
import org.esselunga.utils.exception.ServiceException;

import java.util.List;

@Model
public class ProductServiceImpl implements IProductService {
    @Inject
    ProductMapper productMapper;

    @Inject
    ProductRepository productRepository;

    @Override
    public String insertProduct(ProductDTO productDTO) throws ServiceException {
        try {
            Product product = productMapper.convertDtoToEntity(productDTO);
            productRepository.persist(product);
            return product.getId().toString();

        } catch (Exception ex) {
            throw new ServiceException("ProductServiceImpl.insertProduct error: " + ex.getMessage());
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() throws ServiceException {
        try {
            List<Product> products = productRepository.listAll();
            return productMapper.convertEntityToDto(products);

        } catch (Exception ex) {
            throw new ServiceException("ProductServiceImpl.getAllProducts error: " + ex.getMessage());
        }
    }

    @Override
    public ProductDTO getProductById(String productId) throws ServiceException {
        try {
            Product product = productRepository.findById(new ObjectId(productId));
            return productMapper.convertEntityToDto(product);

        } catch (Exception ex) {
            throw new ServiceException("ProductServiceImpl.getProductById error: " + ex.getMessage());
        }
    }

    @Override
    public void deleteAll() throws ServiceException {
        try {
            productRepository.deleteAll();

        } catch (Exception ex) {
            throw new ServiceException("ProductServiceImpl.deleteAll error: " + ex.getMessage());
        }
    }
}

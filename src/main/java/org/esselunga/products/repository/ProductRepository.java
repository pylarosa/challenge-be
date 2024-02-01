package org.esselunga.products.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.products.entity.Product;

@ApplicationScoped
public class ProductRepository implements PanacheMongoRepository<Product> {
}

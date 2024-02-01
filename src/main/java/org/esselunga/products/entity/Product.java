package org.esselunga.products.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.types.ObjectId;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@MongoEntity(collection = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends PanacheMongoEntity {
    private ObjectId id;
    private String name;
    private Integer quantity;
    private Double price;
}

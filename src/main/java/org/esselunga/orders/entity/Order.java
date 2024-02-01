package org.esselunga.orders.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.esselunga.depots.entity.Depot;
import org.esselunga.products.entity.Product;
import org.esselunga.utils.model.Address;
import org.esselunga.utils.model.Status;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "order")
public class Order extends PanacheMongoEntity {
    private ObjectId id;
    private List<Product> products;
    private Depot depot;
    private Status status;
    private Date orderDate;
    private Date updateDate;
    private Address address;
    private String customer;
    private Boolean updated;
}

package org.esselunga.depots.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.*;
import org.bson.types.ObjectId;
import org.esselunga.utils.model.Address;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@MongoEntity(collection = "depot")
@NoArgsConstructor
@AllArgsConstructor
public class Depot extends PanacheMongoEntity {
    private ObjectId id;
    private String name;
    private Address address;
}

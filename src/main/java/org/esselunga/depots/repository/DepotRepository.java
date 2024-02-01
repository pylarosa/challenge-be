package org.esselunga.depots.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.esselunga.depots.entity.Depot;

@ApplicationScoped
public class DepotRepository implements PanacheMongoRepository<Depot> {
}

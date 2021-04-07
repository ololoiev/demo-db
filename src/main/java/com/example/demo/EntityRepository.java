package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntityRepository extends MongoRepository<Entity, String> {
    Page<Entity> findByType(String type, Pageable pageable);
    Entity findByIdAndType(String id, String type);
    void removeByIdAndType(String id, String type);
}

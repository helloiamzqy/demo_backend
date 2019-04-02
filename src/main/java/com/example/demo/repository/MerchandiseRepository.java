package com.example.demo.repository;

import com.example.demo.entity.Merchandise;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseRepository extends MongoRepository<Merchandise, String> {
}

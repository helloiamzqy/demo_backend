package com.example.demo.repository;

import com.example.demo.entity.Merchandise;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MerchandiseRepository extends MongoRepository<Merchandise, String> {
}

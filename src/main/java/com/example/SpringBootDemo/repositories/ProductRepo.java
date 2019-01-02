package com.example.SpringBootDemo.repositories;

import com.example.SpringBootDemo.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<ProductEntity, Integer> {
    List<ProductEntity> findByUserEntityUsername(String name);
}

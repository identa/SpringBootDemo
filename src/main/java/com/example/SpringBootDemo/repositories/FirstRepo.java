package com.example.SpringBootDemo.repositories;

import com.example.SpringBootDemo.entities.FirstEntity;
import org.springframework.data.repository.CrudRepository;

public interface FirstRepo extends CrudRepository<FirstEntity, Integer> {
}

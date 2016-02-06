package com.quimera.services.repositories;

import com.quimera.services.model.Bar;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Manu on 6/2/16.
 */
public interface BarRepository extends MongoRepository<Bar, String> {

}

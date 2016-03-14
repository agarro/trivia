package com.quimera.repositories;

import com.quimera.model.Trivia;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Manu on 14/3/16.
 */
public interface TriviaRepository extends MongoRepository<Trivia, String> {


}

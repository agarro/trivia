package com.quimera.services.repositories;

/**
 * Created by Manu on 6/2/16.
 */
import com.quimera.services.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String>{

}
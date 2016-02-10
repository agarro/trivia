package com.quimera.services.repositories;

import com.quimera.services.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Manu on 9/2/16.
 */
public interface UserRepository extends MongoRepository<User, String> {

}

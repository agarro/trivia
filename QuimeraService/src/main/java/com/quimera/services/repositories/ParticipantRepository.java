package com.quimera.services.repositories;

import com.quimera.services.model.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Manu on 6/2/16.
 */
public interface ParticipantRepository extends MongoRepository<Participant, String> {

}

package com.Sadetechno.jwt_module.Repository;

import com.Sadetechno.jwt_module.model.OurUsers;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UsersRepo extends MongoRepository<OurUsers, String> {

    // Spring Data automatically implements these methods for you:
    
    // Find user by email
    Optional<OurUsers> findByEmail(String email);

    // Get all users ordered by ID descending (equivalent to your old findAllByOrderByIdDesc)
    List<OurUsers> findAllByOrderByIdDesc();
}
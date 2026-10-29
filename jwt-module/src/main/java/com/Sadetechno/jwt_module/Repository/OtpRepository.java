package com.Sadetechno.jwt_module.Repository;

import com.Sadetechno.jwt_module.model.OtpEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OtpRepository extends MongoRepository<OtpEntity, String> {
    
    // Spring Data automatically generates the database logic for these methods
    Optional<OtpEntity> findByEmailAndOtp(String email, String otp);
    Optional<OtpEntity> findByEmail(String email);
    void deleteByEmail(String email);
    
    List<OtpEntity> findAllByOrderByIdDesc();
    List<OtpEntity> findByEmailOrderByCreatedAtDesc(String email);
}
package com.mrpehlivan.SpringBootCRUD.repository;


import com.mrpehlivan.SpringBootCRUD.model.DeveloperModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<DeveloperModel, Long> {
    Optional<DeveloperModel> findFirstByEmail(String email);
}

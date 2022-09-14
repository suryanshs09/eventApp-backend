package com.example.eventApp.repository;

import com.example.eventApp.entity.EService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<EService, Long> {
}

package com.arathin.artcollectorsregistration.repository;

import com.arathin.artcollectorsregistration.entity.ArtCollector;
import com.arathin.artcollectorsregistration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ArtCollectorRepository extends JpaRepository<ArtCollector, Long>{

    ArtCollector findByEmail(String email);
}

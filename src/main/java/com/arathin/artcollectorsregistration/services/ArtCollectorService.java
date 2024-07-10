package com.arathin.artcollectorsregistration.services;

import com.arathin.artcollectorsregistration.entity.ArtCollector;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ArtCollectorServiceInterface {
    void saveArtCollector(ArtCollector artCollector);
    ArtCollector findArtCollectorByEmail(String email);
    List<ArtCollector> findAllArtCollectors();

}

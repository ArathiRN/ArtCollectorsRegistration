package com.arathin.artcollectorsregistration.services;

import com.arathin.artcollectorsregistration.dto.ArtCollectorDto;
import com.arathin.artcollectorsregistration.entity.ArtCollector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArtCollectorService {
    void saveArtCollector(ArtCollectorDto artCollectorDto);
    ArtCollector findArtCollectorByEmail(String email);

    ArtCollector findByEmail(String email);

    List<ArtCollectorDto> findAllArtCollectors();

}


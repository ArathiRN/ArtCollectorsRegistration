package com.arathin.artcollectorsregistration.services.impl;

import com.arathin.artcollectorsregistration.dto.ArtCollectorDto;
import com.arathin.artcollectorsregistration.entity.ArtCollector;
import com.arathin.artcollectorsregistration.entity.Role;
import com.arathin.artcollectorsregistration.repository.ArtCollectorRepository;
import com.arathin.artcollectorsregistration.repository.RoleRepository;
import com.arathin.artcollectorsregistration.services.ArtCollectorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtCollectorServiceImpl implements ArtCollectorService{
    private ArtCollectorRepository artCollectorRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public ArtCollectorServiceImpl(ArtCollectorRepository artCollectorRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        this.artCollectorRepository = artCollectorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveArtCollector(ArtCollectorDto artCollectorDto) {
        ArtCollector artCollector = new ArtCollector();
        //artCollector.setName(artCollectorDto.getFirstName() + " " + artCollectorDto.getLastName());
        artCollector.setUsername(artCollector.getUsername());
        artCollector.setEmail(artCollectorDto.getEmail());
        artCollector.setPassword(passwordEncoder.encode(artCollectorDto.getPassword()));
        artCollector.setCountry(artCollectorDto.getCountry());

        //Determine the role based on registration criteria
        String roleName;
        if(artCollectorDto.isAdminRegistration()){
            roleName = "ROLE_ADMIN";
        }else{
            roleName= "ROLE_USER";
        }

        //Check if role already exists in database, otherwise create it
        Role role = roleRepository.findByName(roleName);
        if(role == null){
            role = new Role();
            role.setName((roleName));
            roleRepository.save(role);
        }

        //Assign the role to the user
        artCollector.setRoles((Collections.singletonList(role)));
        artCollectorRepository.save(artCollector);

    }

    @Override
    public ArtCollector findArtCollectorByEmail(String email) {
        return null;
    }

    @Override
    public ArtCollector findByEmail(String email) {
        return artCollectorRepository.findByEmail(email);
    }

    @Override
    public List<ArtCollectorDto> findAllArtCollectors() {
        List<ArtCollector> artCollectors = artCollectorRepository.findAll();
        return artCollectors.stream().map((artCollector) -> convertEntityToDto(artCollector))
                .collect(Collectors.toList());

    }

    private ArtCollectorDto convertEntityToDto(ArtCollector artCollector){
        ArtCollectorDto artCollectorDto = new ArtCollectorDto();
        //String[] name = artCollector.getUsername().split(" ");
        String name = artCollector.getUsername();
        artCollectorDto.setUsername(name);
        artCollectorDto.setEmail(artCollector.getEmail());
        return artCollectorDto;
    }

}

package com.arathin.artcollectorsregistration.repository;

import com.arathin.artcollectorsregistration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public class RoleRespository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}

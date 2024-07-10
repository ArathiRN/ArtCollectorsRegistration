package com.arathin.artcollectorsregistration.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GeneratedColumn;
import org.hibernate.annotations.IdGeneratorType;

import java.util.List;

@Data
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<ArtCollector> artCollectorList;


}

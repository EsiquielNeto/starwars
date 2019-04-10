package com.project.starwarsapi.repository;

import com.project.starwarsapi.domain.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Planet findByName(String name);
    boolean existsByName(String name);
}

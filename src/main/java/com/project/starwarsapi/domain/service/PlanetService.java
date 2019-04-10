package com.project.starwarsapi.domain.service;

import com.project.starwarsapi.domain.model.Planet;
import com.project.starwarsapi.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetService extends AbstractService<Planet, Long>{

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    @Override
    JpaRepository<Planet, Long> getRepository() {
        return planetRepository;
    }

    @Override
    Class<Planet> getEntityClass() {
        return Planet.class;
    }

    public Planet findByName(String name) {
        this.existsByName(name);
        return planetRepository.findByName(name);
    }

    private void existsByName(String name) {
        if(!planetRepository.existsByName(name)) {
            throw new IllegalArgumentException();
        }
    }

}

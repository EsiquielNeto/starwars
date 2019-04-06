package com.project.starwarsapi.controller;

import com.project.starwarsapi.domain.model.Planet;
import com.project.starwarsapi.domain.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("starwars/planets")
public class PlanetResource implements AbstractInterface<Planet, Long>{

    private final PlanetService planetService;

    @Autowired
    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<Planet>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findAll(pageable));
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Planet> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<Planet> create(Planet planet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.create(planet));
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Planet> update(Planet planet) {
        planetService.existsById(planet.getId());
        return ResponseEntity.status(HttpStatus.OK).body(planetService.update(planet));
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<Planet> delete(Long id) {
        planetService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

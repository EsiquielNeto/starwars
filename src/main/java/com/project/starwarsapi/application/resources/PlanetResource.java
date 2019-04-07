package com.project.starwarsapi.application.resources;

import com.project.starwarsapi.domain.model.Planet;
import com.project.starwarsapi.domain.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("starwars")
public class PlanetResource implements ResourceInterface<Planet, Long> {

    private final PlanetService planetService;

    @Autowired
    public PlanetResource(PlanetService planetService) {
        this.planetService = planetService;
    }

    @Override
    @GetMapping("planets")
    public ResponseEntity<Page<Planet>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findAll(pageable));
    }

    @Override
    @GetMapping("planets/{id}")
    public ResponseEntity<Planet> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findById(id));
    }

    @GetMapping("planets/name/{name}")
    public ResponseEntity<Planet> findByDescription(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findByName(name));
    }

    @Override
    @PostMapping("planets")
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(planetService.create(planet));
    }

    @Override
    @PutMapping("planets")
    public ResponseEntity<Planet> update(@Valid @RequestBody Planet planet) {
        planetService.existsById(planet.getId());
        return ResponseEntity.status(HttpStatus.OK).body(planetService.update(planet));
    }

    @Override
    @DeleteMapping("planets/{id}")
    public ResponseEntity<Planet> delete(@PathVariable("id") Long id) {
        planetService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}

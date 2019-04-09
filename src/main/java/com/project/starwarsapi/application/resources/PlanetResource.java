package com.project.starwarsapi.application.resources;

import com.project.starwarsapi.domain.model.Planet;
import com.project.starwarsapi.domain.model.PlanetDTO;
import com.project.starwarsapi.domain.model.PlanetPage;
import com.project.starwarsapi.domain.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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

    @GetMapping
    public ResponseEntity<PlanetPage> findAllPlanetAPI(Pageable pageable) {
        ResponseEntity<PlanetPage> dto = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://swapi.co/api/planets/?page=" + (pageable != null && pageable.getPageNumber() != 0 ? Integer.toString(pageable.getPageNumber()) : "1");
            dto = restTemplate.getForEntity(url, PlanetPage.class);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto.getBody(), HttpStatus.OK);
    }

    @Override
    @GetMapping("planets/{id}")
    public ResponseEntity<Planet> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findById(id));
    }

    @GetMapping("planets/name/{name}")
    public ResponseEntity<Planet> findByDescription(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(planetService.findByName(name));
    }

    @Override
    @PostMapping("planets")
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet) {
        planet.setFilms(numberAppearancesFilm(planet.getName()));
        return new ResponseEntity<>(planetService.create(planet), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("planets")
    public ResponseEntity<Planet> update(@Valid @RequestBody Planet planet) {
        planetService.existsById(planet.getId());
        planet.setFilms(numberAppearancesFilm(planet.getName()));
        return ResponseEntity.status(HttpStatus.OK).body(planetService.update(planet));
    }

    @Override
    @DeleteMapping("planets/{id}")
    public ResponseEntity<Planet> delete(@PathVariable("id") Long id) {
        planetService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private int numberAppearancesFilm(String name) {
        RestTemplate restTemplate = new RestTemplate();
        PlanetPage planet = new PlanetPage();
        try {
            String url = "https://swapi.co/api/planets/?search=";
            planet = restTemplate.getForObject(url + name, PlanetPage.class);

        } catch (RestClientException e) {
            throw e;
        }
        return planet.getResults().get(0).getFilms().size();
    }
}

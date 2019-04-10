package com.project.starwarsapi.application.resources;

import com.project.starwarsapi.domain.model.Planet;
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
        return new ResponseEntity<>(planetService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PlanetPage> findAllPlanetAPI(Pageable pageable) {
        ResponseEntity<PlanetPage> dto = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://swapi.co/api/planets/?page=" + (pageable != null && pageable.getPageNumber() != 0 ? Integer.toString(pageable.getPageNumber()) : "1");
            dto = restTemplate.getForEntity(url, PlanetPage.class);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(dto.getBody(), HttpStatus.OK);
    }

    @Override
    @GetMapping("planets/{id}")
    public ResponseEntity<Planet> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(planetService.findById(id), HttpStatus.OK);
    }

    @GetMapping("planets/name/{name}")
    public ResponseEntity<Planet> findByDescription(@PathVariable String name) {
        return new ResponseEntity<>(planetService.findByName(name), HttpStatus.OK);
    }

    @Override
    @PostMapping("planets")
    public ResponseEntity<Planet> create(@Valid @RequestBody Planet planet) {
        planet.setFilms(numberAppearancesFilm(planet.getName()));
        return new ResponseEntity<>(planetService.create(planet), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("planets/{id}")
    public ResponseEntity<Planet> delete(@PathVariable("id") Long id) {
        planetService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private int numberAppearancesFilm(String name) {
        RestTemplate restTemplate = new RestTemplate();
        PlanetPage planet = null;
        try {
            String url = "https://swapi.co/api/planets/?search=";
            planet = restTemplate.getForObject(url + name, PlanetPage.class);

        } catch (RestClientException e) {
            throw e;
        }
        return planet.getResults().get(0).getFilms().size();
    }
}

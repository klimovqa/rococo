package guru.qa.rococo.controller;

import guru.qa.rococo.data.CountryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.GeoService;

import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/api/geo")
public class GeoController {


    private final GeoService service;

    @Autowired
    public GeoController(GeoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<CountryJson> findAll(@PageableDefault Pageable pageable) {
        Page<CountryEntity> allCountries = service.findAll(pageable);
        return allCountries.map(CountryJson::fromEntity);
    }

    @GetMapping("name/{country}")
    public ResponseEntity<CountryJson> findByName(@PathVariable String country) {
        CountryJson resp = CountryJson.fromEntity(service.findByName(country));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping("{uuid}")
    public ResponseEntity<CountryJson> findById(@PathVariable UUID uuid) {
        CountryJson resp = CountryJson.fromEntity(service.findById(uuid));
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}

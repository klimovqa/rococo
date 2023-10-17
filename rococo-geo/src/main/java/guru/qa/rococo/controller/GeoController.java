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


@Slf4j
@RestController
@RequestMapping("/api/country")
public class GeoController {


    private final GeoService service;

    @Autowired
    public GeoController(GeoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<CountryJson> getAllCountries(@PageableDefault Pageable pageable) {
        Page<CountryEntity> allCountries = service.getAllCountries(pageable);
        return allCountries.map(CountryEntity::toCountryJson);
    }

    @GetMapping("{countryName}")
    public CountryJson getCountry(@PathVariable String countryName) {
        return CountryJson.fromEntity(service.getCountryByName(countryName));
    }

    @PostMapping()
    ResponseEntity<CountryJson> addCountry(@RequestBody CountryJson country){
        CountryEntity countryEntity = service.addCountry(country);
        CountryJson addedCountry = CountryJson.fromEntity(countryEntity);
        return new ResponseEntity<>(addedCountry, HttpStatus.CREATED);
    }

}

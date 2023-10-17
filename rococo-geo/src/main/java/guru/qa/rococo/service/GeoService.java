package guru.qa.rococo.service;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.data.repository.GeoRepository;
import guru.qa.rococo.ex.CountryNotFoundException;
import guru.qa.rococo.ex.DuplicateCountryNameException;
import guru.qa.rococo.model.CountryJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class GeoService {

    private final GeoRepository repository;

    @Autowired
    public GeoService(GeoRepository repository) {
        this.repository = repository;
    }


    public Page<CountryEntity> getAllCountries(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public CountryEntity getCountryByName(String countryName) {
        Optional<CountryEntity> countryEntityOptional = repository.findByName(countryName);
        if (countryEntityOptional.isEmpty()){
            throw new CountryNotFoundException("Country not found.");
        }
        return countryEntityOptional.get();
    }

    public CountryEntity addCountry(CountryJson country) {
        CountryEntity entity = CountryEntity.fromJson(country);
        Optional<CountryEntity> countryName = repository.findByName(country.getName());
        if (countryName.isPresent()) {
            throw new DuplicateCountryNameException("A country with this name already exists.");
        }
        return repository.save(entity);
    }

    public void clear() {
        repository.deleteAll();
    }
}
package guru.qa.rococo.service;

import guru.qa.rococo.data.CountryEntity;
import guru.qa.rococo.data.repository.GeoRepository;
import guru.qa.rococo.ex.CountryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
public class GeoService {

    private final GeoRepository repository;

    @Autowired
    public GeoService(GeoRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public Page<CountryEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public CountryEntity findByName(String country) {
        return repository.findByName(country)
                .orElseThrow(() ->
                        new CountryNotFoundException("Country name - " + country + " not found."));
    }

    @Transactional(readOnly = true)
    public CountryEntity findById(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() ->
                        new CountryNotFoundException("Country UUID - " + uuid + " not found."));
    }
}
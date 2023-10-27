package guru.qa.rococo.controller;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.client.GeoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
@Slf4j
public class GeoController {
    GeoClient client;

    @Autowired
    public GeoController(GeoClient client) {
        this.client = client;
    }

    @GetMapping
    public Page<CountryJson> getCountries(@PageableDefault Pageable pageable) {
        return client.findAll(pageable);
    }
}

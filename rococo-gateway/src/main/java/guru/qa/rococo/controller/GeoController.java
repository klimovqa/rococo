package guru.qa.rococo.controller;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.GeoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
@Slf4j
public class GeoController {
    GeoClient geoClient;

    @Autowired
    public GeoController(GeoClient geoClient) {
        this.geoClient = geoClient;
    }

    @GetMapping
    public Page<CountryJson> getCountries(@AuthenticationPrincipal Jwt principal,
                                 @RequestParam Integer size,
                                 @RequestParam Integer page) {
        String username = principal.getClaim("sub");
        return geoClient.getCountries(size, page);
    }
}

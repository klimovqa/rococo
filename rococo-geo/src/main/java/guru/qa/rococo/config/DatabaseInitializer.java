package guru.qa.rococo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.GeoJson;
import guru.qa.rococo.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final GeoService service;

    @Autowired
    public DatabaseInitializer(GeoService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        service.clear();
        ObjectMapper mapper = new ObjectMapper();
        GeoJson geoJson = mapper.readValue(new File("rococo-geo/src/main/resources/country.json"), GeoJson.class);
        System.out.println();
        for (CountryJson country : geoJson.getCountry()) {
            service.addCountry(country);
        }
    }
}

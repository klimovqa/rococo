package guru.qa.rococo.service;

import guru.qa.rococo.model.CountryJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;


public interface GeoClient {

    @Nonnull
    Page<CountryJson> getCountries(@Nonnull Integer size, @Nonnull Integer page);

}

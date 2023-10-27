package guru.qa.rococo.service.client;

import guru.qa.rococo.model.CountryJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface GeoClient {

    @Nonnull
    Page<CountryJson> findAll(Pageable pageable);

    @Nonnull
    CountryJson findById(@Nonnull UUID uuid);
}

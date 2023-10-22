package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.client.GeoClient;
import guru.qa.rococo.service.pageable.MyPage;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Objects.*;

@Component
public class RestGeoClient implements GeoClient {

    private final WebClient webClient;
    private final String rococoGeoBaseUri;

    @Autowired
    public RestGeoClient(WebClient webClient,
                         @Value("${rococo-geo.base-uri}") String rococoGeoBaseUri) {
        this.webClient = webClient;
        this.rococoGeoBaseUri = rococoGeoBaseUri;
    }

    @Nonnull
    @Override
    public Page<CountryJson> getCountries(@Nonnull Integer size, @Nonnull Integer page) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/geo?size=%d&page=%d",
                        rococoGeoBaseUri,
                        size,
                        page
                        ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<CountryJson>>() {
                })
                .block());

    }

    @Nonnull
    @Override
    public CountryJson getCountryById(@Nonnull UUID uuid) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/geo/%s",
                        rococoGeoBaseUri,
                        uuid
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(CountryJson.class)
                .block());
    }
}

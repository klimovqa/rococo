package guru.qa.rococo.service.imp;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.service.GeoClient;
import guru.qa.rococo.service.pageable.CountryPage;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;

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

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CountryPage>() {
                })
                .block();

    }
}

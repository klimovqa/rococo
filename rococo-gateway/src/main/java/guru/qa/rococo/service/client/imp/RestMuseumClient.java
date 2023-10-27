package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.service.client.GeoClient;
import guru.qa.rococo.service.client.MuseumClient;
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

import static java.lang.String.format;
import static java.util.Objects.*;

@Component
public class RestMuseumClient implements MuseumClient {

    private final WebClient webClient;
    private final String rococoMuseumBaseUri;
    private final GeoClient geoClient;

    @Autowired
    public RestMuseumClient(WebClient webClient,
                            @Value("${rococo-museum.base-uri}") String rococoMuseumBaseUri,
                            GeoClient geoClient) {
        this.webClient = webClient;
        this.rococoMuseumBaseUri = rococoMuseumBaseUri;
        this.geoClient = geoClient;
    }

    @Nonnull
    @Override
    public Page<MuseumJson> getMuseums(@Nonnull Integer size, @Nonnull Integer page) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum?size=%d&page=%d",
                        rococoMuseumBaseUri,
                        size,
                        page
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<MuseumJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public Page<MuseumJson> search(@Nonnull String title) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum/search?title=%s",
                        rococoMuseumBaseUri,
                        title
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<MuseumJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public MuseumJson getMuseum(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum/%s",
                        rococoMuseumBaseUri,
                        id
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(MuseumJson.class)
                .block());
    }

    @Nonnull
    @Override
    public MuseumJson updateMuseum(@Nonnull MuseumJson museumJson) {
        CountryJson country = geoClient.findById(museumJson.getGeo().getCountry().getId());
        museumJson.getGeo().getCountry().setName(country.getName());

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum",
                        rococoMuseumBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(museumJson)
                .retrieve()
                .bodyToMono(MuseumJson.class)
                .block());
    }

    @Nonnull
    @Override
    public MuseumJson addMuseum(@Nonnull MuseumJson museumJson) {
        CountryJson country = geoClient.findById(museumJson.getGeo().getCountry().getId());
        museumJson.getGeo().getCountry().setName(country.getName());

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum",
                        rococoMuseumBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(museumJson)
                .retrieve()
                .bodyToMono(MuseumJson.class)
                .block());
    }

}

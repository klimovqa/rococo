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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;
import static java.util.Objects.*;

@Component
public class RestMuseumClient implements MuseumClient {

    private final WebClient webClient;
    private final String uriMuseumService;
    private final GeoClient geoClient;

    @Autowired
    public RestMuseumClient(WebClient webClient,
                            @Value("${rococo-museum.base-uri}") String uriMuseumService,
                            GeoClient geoClient) {
        this.webClient = webClient;
        this.uriMuseumService = uriMuseumService;
        this.geoClient = geoClient;
    }

    @Nonnull
    @Override
    public Page<MuseumJson> findAll(String title, Pageable page) {
        URI uri;
        if (title != null) {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/museum/title/%s",
                            uriMuseumService,
                            title
                    ))
                    .build()
                    .toUri();
        } else {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/museum?size=%d&page=%d",
                            uriMuseumService,
                            page.getPageSize(),
                            page.getPageNumber()
                    ))
                    .build()
                    .toUri();
        }


        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<MuseumJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public MuseumJson findById(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum/%s",
                        uriMuseumService,
                        id))
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
    public MuseumJson update(@Nonnull MuseumJson museum) {
        CountryJson country = geoClient.findById(museum.getGeo().getCountry().getId());
        museum.getGeo().getCountry().setName(country.getName());

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum",
                        uriMuseumService
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(museum)
                .retrieve()
                .bodyToMono(MuseumJson.class)
                .block());
    }

    @Nonnull
    @Override
    public MuseumJson add(@Nonnull MuseumJson museum) {
        CountryJson country = geoClient.findById(museum.getGeo().getCountry().getId());
        museum.getGeo().getCountry().setName(country.getName());

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/museum", uriMuseumService))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(museum)
                .retrieve()
                .bodyToMono(MuseumJson.class)
                .block());
    }

}

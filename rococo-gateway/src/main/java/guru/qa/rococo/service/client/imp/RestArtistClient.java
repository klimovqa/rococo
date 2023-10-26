package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.client.GeoClient;
import guru.qa.rococo.service.client.ArtistClient;
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
public class RestArtistClient implements ArtistClient {

    private final WebClient webClient;
    private final String uriArtistService;
    private final GeoClient geoClient;

    @Autowired
    public RestArtistClient(WebClient webClient,
                            @Value("${rococo-artist.base-uri}") String uriArtistService,
                            GeoClient geoClient) {
        this.webClient = webClient;
        this.uriArtistService = uriArtistService;
        this.geoClient = geoClient;
    }

    @Nonnull
    @Override
    public Page<ArtistJson> findAll(String name, Pageable pageable) {
        URI uri;
        if (name != null) {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/artist/name/%s",
                            uriArtistService,
                            name
                    ))
                    .build()
                    .toUri();

        } else {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/artist?size=%d&page=%d",
                            uriArtistService,
                            pageable.getPageSize(),
                            pageable.getPageNumber()
                    ))
                    .build()
                    .toUri();
        }

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<ArtistJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public ArtistJson findById(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist/%s",
                        uriArtistService,
                        id
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(ArtistJson.class)
                .block());
    }

    @Nonnull
    @Override
    public ArtistJson update(@Nonnull ArtistJson artist) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist",
                        uriArtistService
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(artist)
                .retrieve()
                .bodyToMono(ArtistJson.class)
                .block());
    }

    @Nonnull
    @Override
    public ArtistJson add(@Nonnull ArtistJson artist) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist",
                        uriArtistService
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(artist)
                .retrieve()
                .bodyToMono(ArtistJson.class)
                .block());
    }

}

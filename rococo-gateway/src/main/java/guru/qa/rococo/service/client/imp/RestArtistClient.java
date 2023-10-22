package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.CountryJson;
import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.service.client.GeoClient;
import guru.qa.rococo.service.client.ArtistClient;
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

import static java.lang.String.format;
import static java.util.Objects.*;

@Component
public class RestArtistClient implements ArtistClient {

    private final WebClient webClient;
    private final String rococoArtistBaseUri;
    private final GeoClient geoClient;

    @Autowired
    public RestArtistClient(WebClient webClient,
                            @Value("${rococo-artist.base-uri}") String rococoArtistBaseUri,
                            GeoClient geoClient) {
        this.webClient = webClient;
        this.rococoArtistBaseUri = rococoArtistBaseUri;
        this.geoClient = geoClient;
    }

    @Nonnull
    @Override
    public Page<ArtistJson> getArtists(@Nonnull Integer size, @Nonnull Integer page) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist?size=%d&page=%d",
                        rococoArtistBaseUri,
                        size,
                        page
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<ArtistJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public Page<ArtistJson> search(@Nonnull String name) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist/search?name=%s",
                        rococoArtistBaseUri,
                        name
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<ArtistJson>>() {
                })
                .block());
    }

    @Nonnull
    @Override
    public ArtistJson getArtist(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist/%s",
                        rococoArtistBaseUri,
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
    public ArtistJson updateArtist(@Nonnull ArtistJson artistJson) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist",
                        rococoArtistBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(artistJson)
                .retrieve()
                .bodyToMono(ArtistJson.class)
                .block());
    }

    @Nonnull
    @Override
    public ArtistJson addArtist(@Nonnull ArtistJson artistJson) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/artist",
                        rococoArtistBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(artistJson)
                .retrieve()
                .bodyToMono(ArtistJson.class)
                .block());
    }

}

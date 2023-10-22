package guru.qa.rococo.service.client.imp;

import guru.qa.rococo.model.ArtistJson;
import guru.qa.rococo.model.MuseumJson;
import guru.qa.rococo.model.PaintingJson;
import guru.qa.rococo.service.client.ArtistClient;
import guru.qa.rococo.service.client.MuseumClient;
import guru.qa.rococo.service.client.PaintingClient;
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
public class RestPaintingClient implements PaintingClient {

    private final WebClient webClient;
    private final String rococoPaintingBaseUri;
    private final ArtistClient artistClient;
    private final MuseumClient museumClient;

    @Autowired
    public RestPaintingClient(WebClient webClient,
                              @Value("${rococo-painting.base-uri}") String rococoPaintingBaseUri,
                              ArtistClient artistClient,
                              MuseumClient museumClient) {
        this.webClient = webClient;
        this.rococoPaintingBaseUri = rococoPaintingBaseUri;
        this.artistClient = artistClient;
        this.museumClient = museumClient;
    }

    @Nonnull
    @Override
    public Page<PaintingJson> getPaintings(@Nonnull Integer size, @Nonnull Integer page) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting?size=%d&page=%d",
                        rococoPaintingBaseUri,
                        size,
                        page
                ))
                .build()
                .toUri();

        Page<PaintingJson> results = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<PaintingJson>>() {
                })
                .block();

        if (results.getContent() != null && !results.getContent().isEmpty()) {
            results.getContent().forEach(painting -> {
                getInfoAboutArtistAndMuseums(painting);
            });
        }

        return results;
    }

    @Nonnull
    @Override
    public Page<PaintingJson> getPaintingsByAuthor(@Nonnull String id, @Nonnull Integer size, @Nonnull Integer page) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting/author/%s?size=%d&page=%d",
                        rococoPaintingBaseUri,
                        id,
                        size,
                        page
                ))
                .build()
                .toUri();

        Page<PaintingJson> results = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<PaintingJson>>() {
                })
                .block();

        if (results.getContent() != null && !results.getContent().isEmpty()) {
            results.getContent().forEach(painting -> {
                getInfoAboutArtistAndMuseums(painting);
            });
        }

        return results;
    }

    @Nonnull
    @Override
    public Page<PaintingJson> search(@Nonnull String title) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting/search?title=%s",
                        rococoPaintingBaseUri,
                        title
                ))
                .build()
                .toUri();

        MyPage<PaintingJson> results = requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<PaintingJson>>() {
                })
                .block());


        if (results.getContent() != null && !results.getContent().isEmpty()) {
            results.getContent().forEach(painting -> {
                getInfoAboutArtistAndMuseums(painting);
            });
        }
        return results;
    }

    @Nonnull
    @Override
    public PaintingJson getPainting(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting/%s",
                        rococoPaintingBaseUri,
                        id
                ))
                .build()
                .toUri();

        PaintingJson painting = requireNonNull(webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(PaintingJson.class)
                .block());

        getInfoAboutArtistAndMuseums(painting);

        return painting;
    }

    private void getInfoAboutArtistAndMuseums(PaintingJson painting) {
        ArtistJson artist = artistClient.getArtist(painting.getArtist().getId().toString());
        painting.setArtist(artist);
        MuseumJson museum = museumClient.getMuseum(painting.getMuseum().getId().toString());
        painting.setMuseum(museum);
    }

    @Nonnull
    @Override
    public PaintingJson updatePainting(@Nonnull PaintingJson paintingJson) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting",
                        rococoPaintingBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(paintingJson)
                .retrieve()
                .bodyToMono(PaintingJson.class)
                .block());
    }

    @Nonnull
    @Override
    public PaintingJson addPainting(@Nonnull PaintingJson paintingJson) {

        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting",
                        rococoPaintingBaseUri
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(paintingJson)
                .retrieve()
                .bodyToMono(PaintingJson.class)
                .block());
    }

}

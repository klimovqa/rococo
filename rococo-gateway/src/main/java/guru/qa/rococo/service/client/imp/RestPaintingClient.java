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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;
import static java.util.Objects.*;

@Component
public class RestPaintingClient implements PaintingClient {

    private final WebClient webClient;
    private final String uriPaintingService;
    private final ArtistClient artistClient;
    private final MuseumClient museumClient;

    @Autowired
    public RestPaintingClient(WebClient webClient,
                              @Value("${rococo-painting.base-uri}") String uriPaintingService,
                              ArtistClient artistClient,
                              MuseumClient museumClient) {
        this.webClient = webClient;
        this.uriPaintingService = uriPaintingService;
        this.artistClient = artistClient;
        this.museumClient = museumClient;
    }

    @Nonnull
    @Override
    public Page<PaintingJson> findAll(String title, Pageable page) {
        URI uri;
        if (title != null) {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/painting/title/%s",
                            uriPaintingService,
                            title
                    ))
                    .build()
                    .toUri();
        } else {
            uri = UriComponentsBuilder
                    .fromHttpUrl(format("%s/api/painting?size=%d&page=%d",
                            uriPaintingService,
                            page.getPageSize(),
                            page.getPageNumber()
                    ))
                    .build()
                    .toUri();
        }

        Page<PaintingJson> results = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<MyPage<PaintingJson>>() {
                })
                .block();

        if (results.getContent() != null && !results.getContent().isEmpty()) {
            results.getContent().forEach(this::getInfoAboutArtistAndMuseums);
        }
        return results;
    }

    @Nonnull
    @Override
    public Page<PaintingJson> findByAuthor(@Nonnull String uuid, @Nonnull Pageable page) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting/author/%s?size=%d&page=%d",
                        uriPaintingService,
                        uuid,
                        page.getPageSize(),
                        page.getPageNumber()
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
            results.getContent().forEach(this::getInfoAboutArtistAndMuseums);
        }
        return results;
    }

    @Nonnull
    @Override
    public PaintingJson findById(@Nonnull String id) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting/%s",
                        uriPaintingService,
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

    @Nonnull
    @Override
    public PaintingJson update(@Nonnull PaintingJson painting) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting",
                        uriPaintingService
                ))
                .build()
                .toUri();

        PaintingJson result = requireNonNull(webClient.patch()
                .uri(uri)
                .bodyValue(painting)
                .retrieve()
                .bodyToMono(PaintingJson.class)
                .block());

        getInfoAboutArtistAndMuseums(result);
        return result;
    }

    @Nonnull
    @Override
    public PaintingJson add(@Nonnull PaintingJson painting) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(format("%s/api/painting",
                        uriPaintingService
                ))
                .build()
                .toUri();

        return requireNonNull(webClient.post()
                .uri(uri)
                .bodyValue(painting)
                .retrieve()
                .bodyToMono(PaintingJson.class)
                .block());
    }


    private void getInfoAboutArtistAndMuseums(PaintingJson painting) {
        ArtistJson artist = artistClient.findById(painting.getArtist().getId().toString());
        painting.setArtist(artist);
        MuseumJson museum = museumClient.findById(painting.getMuseum().getId().toString());
        painting.setMuseum(museum);
    }

}

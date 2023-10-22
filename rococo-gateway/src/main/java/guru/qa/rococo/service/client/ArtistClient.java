package guru.qa.rococo.service.client;

import guru.qa.rococo.model.ArtistJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;


public interface ArtistClient {

    @Nonnull
    Page<ArtistJson> getArtists(@Nonnull Integer size, @Nonnull Integer page);
    @Nonnull
    Page<ArtistJson> search(@Nonnull String title);
    @Nonnull
    ArtistJson getArtist(@Nonnull String id);

    @Nonnull
    ArtistJson updateArtist(@Nonnull ArtistJson artistJson);

    @Nonnull
    ArtistJson addArtist(@Nonnull ArtistJson artistJson);
}

package guru.qa.rococo.service.client;

import guru.qa.rococo.model.ArtistJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ArtistClient {

    @Nonnull
    Page<ArtistJson> findAll(@Nonnull String name, @Nonnull Pageable pageable);
    @Nonnull
    ArtistJson findById(@Nonnull String id);

    @Nonnull
    ArtistJson update(@Nonnull ArtistJson artistJson);

    @Nonnull
    ArtistJson add(@Nonnull ArtistJson artistJson);
}

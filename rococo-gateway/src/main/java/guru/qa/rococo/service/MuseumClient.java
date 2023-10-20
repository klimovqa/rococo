package guru.qa.rococo.service;

import guru.qa.rococo.model.MuseumJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;


public interface MuseumClient {

    @Nonnull
    Page<MuseumJson> getMuseums(@Nonnull Integer size, @Nonnull Integer page);
    @Nonnull
    Page<MuseumJson> search(@Nonnull String title);
    @Nonnull
    MuseumJson getMuseum(@Nonnull String id);

    @Nonnull
    MuseumJson updateMuseum(@Nonnull MuseumJson museumJson);

    @Nonnull
    MuseumJson addMuseum(@Nonnull MuseumJson museumJson);
}

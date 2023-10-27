package guru.qa.rococo.service.client;

import guru.qa.rococo.model.MuseumJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MuseumClient {

    @Nonnull
    Page<MuseumJson> findAll(String title, Pageable page);
    @Nonnull
    MuseumJson findById(@Nonnull String id);

    @Nonnull
    MuseumJson update(@Nonnull MuseumJson museumJson);

    @Nonnull
    MuseumJson add(@Nonnull MuseumJson museumJson);
}

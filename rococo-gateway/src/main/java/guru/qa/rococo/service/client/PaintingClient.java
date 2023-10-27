package guru.qa.rococo.service.client;

import guru.qa.rococo.model.PaintingJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PaintingClient {

    @Nonnull
    Page<PaintingJson> findAll(String title, Pageable page);

    @Nonnull
    Page<PaintingJson> findByAuthor(@Nonnull String uuid, @Nonnull Pageable page);
    @Nonnull
    PaintingJson findById(@Nonnull String id);

    @Nonnull
    PaintingJson update(@Nonnull PaintingJson paintingJson);

    @Nonnull
    PaintingJson add(@Nonnull PaintingJson paintingJson);
}

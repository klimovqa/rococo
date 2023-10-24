package guru.qa.rococo.service.client;

import guru.qa.rococo.model.PaintingJson;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;


public interface PaintingClient {

    @Nonnull
    Page<PaintingJson> getPaintings(@Nonnull Integer size, @Nonnull Integer page);

    @Nonnull
    Page<PaintingJson> getPaintingsByAuthor(@Nonnull String id,
                                            @Nonnull Integer size,
                                            @Nonnull Integer page);
    @Nonnull
    Page<PaintingJson> search(@Nonnull String title);
    @Nonnull
    PaintingJson getPainting(@Nonnull String id);

    @Nonnull
    PaintingJson updatePainting(@Nonnull PaintingJson paintingJson);

    @Nonnull
    PaintingJson addPainting(@Nonnull PaintingJson paintingJson);
}

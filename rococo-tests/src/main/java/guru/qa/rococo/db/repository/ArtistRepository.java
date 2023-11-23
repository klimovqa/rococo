package guru.qa.rococo.db.repository;

import guru.qa.rococo.db.entity.artist.ArtistEntity;

import java.util.List;
import java.util.UUID;

public interface ArtistRepository {

    void create(ArtistEntity artist);

    ArtistEntity findByNameArtist(String name);

    void removeAll();

    ArtistEntity findById(UUID uuid);

    List<ArtistEntity> findByArtist(int size, int page);
}

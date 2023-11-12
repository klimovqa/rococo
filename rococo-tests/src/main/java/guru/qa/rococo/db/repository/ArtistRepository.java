package guru.qa.rococo.db.repository;

import guru.qa.rococo.db.entity.artist.ArtistEntity;

public interface ArtistRepository {

    void create(ArtistEntity artist);

    ArtistEntity findByNameArtist(String name);

    void removeAll();
}

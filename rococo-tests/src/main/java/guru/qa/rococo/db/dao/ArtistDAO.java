package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.artist.ArtistEntity;

import java.util.List;
import java.util.UUID;

public interface ArtistDAO {

    void createArtist(ArtistEntity artist);

    ArtistEntity findByName(String name);

    void removeAll();

    ArtistEntity findById(UUID uuid);

    List<ArtistEntity> findByArtist(int size, int page);
}

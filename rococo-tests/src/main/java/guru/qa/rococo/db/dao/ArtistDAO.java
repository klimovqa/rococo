package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.artist.ArtistEntity;

public interface ArtistDAO {

    void createArtist(ArtistEntity artist);

    ArtistEntity findByName(String name);

    void removeAll();
}

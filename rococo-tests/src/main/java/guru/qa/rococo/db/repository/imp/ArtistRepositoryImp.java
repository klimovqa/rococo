package guru.qa.rococo.db.repository.imp;

import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.dao.impl.ArtistDAOHibernate;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.repository.ArtistRepository;

public class ArtistRepositoryImp implements ArtistRepository {

    private final ArtistDAO artistDAO;

    public ArtistRepositoryImp() {
        artistDAO = new ArtistDAOHibernate();
    }

    @Override
    public void create(ArtistEntity artist) {
        artistDAO.createArtist(artist);
    }

    @Override
    public ArtistEntity findByNameArtist(String name) {
        return artistDAO.findByName(name);
    }

    @Override
    public void removeAll() {
        artistDAO.removeAll();
    }
}

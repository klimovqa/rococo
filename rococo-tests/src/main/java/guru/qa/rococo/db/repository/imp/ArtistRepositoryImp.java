package guru.qa.rococo.db.repository.imp;

import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.dao.impl.ArtistDAOHibernate;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.repository.ArtistRepository;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

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
    @Step("Поиск художника в БД")
    public ArtistEntity findByNameArtist(String name) {
        return artistDAO.findByName(name);
    }

    @Override
    public void removeAll() {
        artistDAO.removeAll();
    }

    @Override
    @Step("Поиск художника в БД по Id")
    public ArtistEntity findById(UUID uuid) {
        return artistDAO.findById(uuid);
    }

    @Override
    @Step("Поиск художников в БД")
    public List<ArtistEntity> findByArtist(int size, int page) {
        return artistDAO.findByArtist(size, page);
    }
}

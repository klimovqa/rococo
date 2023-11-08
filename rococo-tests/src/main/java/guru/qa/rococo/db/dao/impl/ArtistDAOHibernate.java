package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

public class ArtistDAOHibernate extends JpaService implements ArtistDAO {

    public ArtistDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.ARTIST).createEntityManager());
    }

    @Override
    public void createArtist(ArtistEntity artist) {
        persist(artist);
    }
}

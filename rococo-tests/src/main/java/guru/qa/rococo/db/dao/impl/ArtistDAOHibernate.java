package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.ArtistDAO;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

import java.util.List;
import java.util.UUID;

public class ArtistDAOHibernate extends JpaService implements ArtistDAO {

    public ArtistDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.ARTIST).createEntityManager());
    }

    @Override
    public void createArtist(ArtistEntity artist) {
        persist(artist);
    }

    @Override
    public ArtistEntity findByName(String name) {
        return em.createQuery("select u from ArtistEntity u where u.name=:name",
                        ArtistEntity.class)
                .setParameter("name", name)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public void removeAll() {
        removeAll("delete from ArtistEntity");
    }

    @Override
    public ArtistEntity findById(UUID uuid) {
        return em.createQuery("select u from ArtistEntity u where u.id=:uuid",
                        ArtistEntity.class)
                .setParameter("uuid", uuid)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public List<ArtistEntity> findByArtist(int size, int page) {
        return em.createQuery("select u from ArtistEntity u", ArtistEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}

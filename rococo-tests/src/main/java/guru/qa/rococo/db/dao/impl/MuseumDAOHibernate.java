package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.MuseumDAO;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

import java.util.List;
import java.util.UUID;

public class MuseumDAOHibernate extends JpaService implements MuseumDAO {

    public MuseumDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.MUSEUM).createEntityManager());
    }

    @Override
    public void createMuseum(MuseumEntity museum) {
        persist(museum);
    }

    @Override
    public MuseumEntity findByName(String title) {
        return em.createQuery("select u from MuseumEntity u where u.title=:title",
                        MuseumEntity.class)
                .setParameter("title", title)
                .getResultStream().findFirst().orElse(null);
    }

    @Override
    public void removeAll() {
        removeAll("delete from MuseumEntity ");
    }

    @Override
    public List<MuseumEntity> findMuseum(int size, int page) {
        return em.createQuery("select u from MuseumEntity u", MuseumEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public MuseumEntity findById(UUID uuid) {
        return em.createQuery("select u from MuseumEntity u where u.id=:uuid",
                        MuseumEntity.class)
                .setParameter("uuid", uuid)
                .getResultStream().findFirst().orElse(null);
    }
}

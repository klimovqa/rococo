package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.MuseumDAO;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

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
}

package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.PaintingDAO;
import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.entity.painting.PaintingEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

import java.util.List;

public class PaintingDAOHibernate extends JpaService implements PaintingDAO {

    public PaintingDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.PAINTING).createEntityManager());
    }

    @Override
    public void create(PaintingEntity painting) {
        persist(painting);
    }

    @Override
    public void removeAll() {
        removeAll("delete from PaintingEntity");
    }

    @Override
    public PaintingEntity findByNamePainting(String painting) {
        return em.createQuery("select p from PaintingEntity p where p.title =: painting", PaintingEntity.class)
                .setParameter("painting", painting)
                .getSingleResult();
    }

    public List<PaintingEntity> findByPainting(int size, int page) {
        return em.createQuery("select u from PaintingEntity u", PaintingEntity.class)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}

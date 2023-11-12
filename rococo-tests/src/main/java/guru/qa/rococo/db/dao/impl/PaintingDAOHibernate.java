package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.PaintingDAO;
import guru.qa.rococo.db.entity.painting.PaintingEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

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
}

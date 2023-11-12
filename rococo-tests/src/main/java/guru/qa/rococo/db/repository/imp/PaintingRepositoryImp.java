package guru.qa.rococo.db.repository.imp;

import guru.qa.rococo.db.dao.PaintingDAO;
import guru.qa.rococo.db.dao.impl.PaintingDAOHibernate;
import guru.qa.rococo.db.entity.painting.PaintingEntity;
import guru.qa.rococo.db.repository.PaintingRepository;

public class PaintingRepositoryImp implements PaintingRepository {

    private final PaintingDAO paintingDAO;

    public PaintingRepositoryImp() {
        paintingDAO = new PaintingDAOHibernate();
    }

    @Override
    public void create(PaintingEntity painting) {
        paintingDAO.create(painting);
    }

    @Override
    public void removeAll() {
        paintingDAO.removeAll();
    }
}

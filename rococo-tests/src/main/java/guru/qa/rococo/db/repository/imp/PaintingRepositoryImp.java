package guru.qa.rococo.db.repository.imp;

import guru.qa.rococo.db.dao.PaintingDAO;
import guru.qa.rococo.db.dao.impl.PaintingDAOHibernate;
import guru.qa.rococo.db.entity.painting.PaintingEntity;
import guru.qa.rococo.db.repository.PaintingRepository;
import io.qameta.allure.Step;

import java.util.List;

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

    @Override
    @Step("Поиск картины в БД")
    public PaintingEntity findByNamePainting(String painting) {
        return paintingDAO.findByNamePainting(painting);
    }

    @Override
    @Step("Поиск картин в БД")
    public List<PaintingEntity> findByPainting(int size, int page) {
        return paintingDAO.findByPainting(size, page);
    }
}

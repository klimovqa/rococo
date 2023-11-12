package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.painting.PaintingEntity;

public interface PaintingDAO {

    void create(PaintingEntity painting);

    void removeAll();
}

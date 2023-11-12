package guru.qa.rococo.db.repository;

import guru.qa.rococo.db.entity.painting.PaintingEntity;

public interface PaintingRepository {

    void create(PaintingEntity painting);

    void removeAll();
}

package guru.qa.rococo.db.repository;

import guru.qa.rococo.db.entity.painting.PaintingEntity;

import java.util.List;

public interface PaintingRepository {

    void create(PaintingEntity painting);

    void removeAll();

    PaintingEntity findByNamePainting(String painting);

    List<PaintingEntity> findByPainting(int size, int page);
}

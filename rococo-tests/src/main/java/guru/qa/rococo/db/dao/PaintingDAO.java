package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.artist.ArtistEntity;
import guru.qa.rococo.db.entity.painting.PaintingEntity;

import java.util.List;

public interface PaintingDAO {

    void create(PaintingEntity painting);

    void removeAll();

    PaintingEntity findByNamePainting(String painting);

    List<PaintingEntity> findByPainting(int size, int page);
}

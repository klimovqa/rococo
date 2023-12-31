package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.museum.MuseumEntity;

import java.util.List;
import java.util.UUID;

public interface MuseumDAO {

    void createMuseum (MuseumEntity museum);

    MuseumEntity findByName(String title);

    void removeAll();

    List<MuseumEntity> findMuseum(int size, int page);

    MuseumEntity findById(UUID uuid);
}

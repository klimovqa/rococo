package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.entity.museum.MuseumEntity;

public interface MuseumDAO {

    void createMuseum (MuseumEntity museum);

    MuseumEntity findByName(String title);

    void removeAll();
}

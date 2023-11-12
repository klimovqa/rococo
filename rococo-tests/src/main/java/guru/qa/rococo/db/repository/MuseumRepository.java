package guru.qa.rococo.db.repository;

import guru.qa.rococo.db.entity.geo.CountryEntity;
import guru.qa.rococo.db.entity.museum.MuseumEntity;

public interface MuseumRepository {

    void create(MuseumEntity museum);

    CountryEntity findByNameCountry(String name);

    MuseumEntity findByNameMuseum(String title);

    void removeAll();
}

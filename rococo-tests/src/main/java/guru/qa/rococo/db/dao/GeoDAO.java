package guru.qa.rococo.db.dao;

import guru.qa.rococo.db.entity.geo.CountryEntity;

public interface GeoDAO {

    CountryEntity findByCountryName(String name);
}

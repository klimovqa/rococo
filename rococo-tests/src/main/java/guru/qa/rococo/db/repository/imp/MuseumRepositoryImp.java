package guru.qa.rococo.db.repository.imp;

import guru.qa.rococo.db.dao.GeoDAO;
import guru.qa.rococo.db.dao.MuseumDAO;
import guru.qa.rococo.db.dao.impl.GeoDAOHibernate;
import guru.qa.rococo.db.dao.impl.MuseumDAOHibernate;
import guru.qa.rococo.db.entity.geo.CountryEntity;
import guru.qa.rococo.db.entity.museum.MuseumEntity;
import guru.qa.rococo.db.repository.MuseumRepository;
import io.qameta.allure.Step;

import java.util.List;
import java.util.UUID;

public class MuseumRepositoryImp implements MuseumRepository {

    private final MuseumDAO museumDAO;
    private final GeoDAO geoDAO;

    public MuseumRepositoryImp() {
        museumDAO = new MuseumDAOHibernate();
        geoDAO = new GeoDAOHibernate();
    }

    @Override
    public void create(MuseumEntity museum) {
        museumDAO.createMuseum(museum);
    }

    @Override
    public CountryEntity findByNameCountry(String name) {
        return geoDAO.findByCountryName(name);
    }

    @Override
    @Step("Поиск музея в БД")
    public MuseumEntity findByNameMuseum(String title) {
        return museumDAO.findByName(title);
    }

    @Override
    public void removeAll() {
        museumDAO.removeAll();
    }

    @Override
    @Step("Поиск музеев в БД")
    public List<MuseumEntity> findByMuseum(int size, int page) {
        return museumDAO.findMuseum(size, page);
    }

    @Override
    @Step("Поиск музея в БД по Id")
    public MuseumEntity findById(UUID uuid) {
        return museumDAO.findById(uuid);
    }
}

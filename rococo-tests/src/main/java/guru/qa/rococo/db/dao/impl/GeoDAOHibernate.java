package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.GeoDAO;
import guru.qa.rococo.db.entity.geo.CountryEntity;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;

public class GeoDAOHibernate extends JpaService implements GeoDAO {

    public GeoDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.GEO).createEntityManager());
    }

    @Override
    public CountryEntity findByCountryName(String name) {
        return em.createQuery("select u from CountryEntity u where u.name=:name",
                        CountryEntity.class)
                .setParameter("name", name)
                .getResultStream().findFirst().orElse(null);
    }
}

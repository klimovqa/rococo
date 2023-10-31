package guru.qa.rococo.db.dao.impl;

import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;
import guru.qa.rococo.db.model.auth.AuthUserEntity;

import java.util.UUID;

public class AuthUserDAOHibernate extends JpaService implements AuthUserDAO {

    public AuthUserDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.AUTH).createEntityManager());
    }

    @Override
    public void createUser(AuthUserEntity user) {
        user.setPassword(pe.encode(user.getPassword()));
        persist(user);
    }

    @Override
    public AuthUserEntity updateUser(AuthUserEntity user) {
        AuthUserEntity userByName = findByName(user.getUsername());
        return merge(userByName);
    }

    @Override
    public void deleteUser(AuthUserEntity user) {
        AuthUserEntity userByName = findByName(user.getUsername());
        remove(userByName);
    }

    @Override
    public AuthUserEntity findById(UUID userId) {
        return em.createQuery("select u from AuthUserEntity u where u.id=:userId",
                        AuthUserEntity.class)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Override
    public AuthUserEntity findByName(String name) {
        return em.createQuery("select u from AuthUserEntity u where u.username=:name",
                        AuthUserEntity.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}

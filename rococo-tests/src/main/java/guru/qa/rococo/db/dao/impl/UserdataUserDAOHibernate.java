package guru.qa.rococo.db.dao.impl;


import guru.qa.rococo.db.ServiceDB;
import guru.qa.rococo.db.dao.UserDataUserDAO;
import guru.qa.rococo.db.jpa.EntityManagerFactoryProvider;
import guru.qa.rococo.db.jpa.JpaService;
import guru.qa.rococo.db.model.userdata.UserDataEntity;

import java.util.List;
import java.util.UUID;

public class UserdataUserDAOHibernate extends JpaService implements UserDataUserDAO {

    public UserdataUserDAOHibernate() {
        super(EntityManagerFactoryProvider.INSTANCE.getDataSource(ServiceDB.USERDATA).createEntityManager());
    }


    @Override
    public void createUser(UserDataEntity user) {
        persist(user);
    }

    @Override
    public void deleteUserById(UUID userId) {
        UserDataEntity user = em.createQuery("select u from UserDataEntity u where u.id=:userId",
                        UserDataEntity.class)
                .setParameter("userId", userId)
                .getSingleResult();
        remove(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        remove(findByUsername(username));
    }

    @Override
    public UserDataEntity findByUsername(String username) {
        return em.createQuery("select u from UserDataEntity u where u.username=:username",
                        UserDataEntity.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void updateUser(UserDataEntity user) {
        merge(user);
    }
}

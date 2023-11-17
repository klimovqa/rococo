package guru.qa.rococo.db.repository.imp;


import guru.qa.rococo.db.dao.AuthUserDAO;
import guru.qa.rococo.db.dao.UserDataUserDAO;
import guru.qa.rococo.db.dao.impl.AuthUserDAOHibernate;
import guru.qa.rococo.db.dao.impl.UserdataUserDAOHibernate;
import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import guru.qa.rococo.db.entity.userdata.UserDataEntity;
import guru.qa.rococo.db.repository.UserRepository;

public class UserRepositoryImp implements UserRepository {
    private final AuthUserDAO authUserDAO;
    private final UserDataUserDAO udUserDAO;

    public UserRepositoryImp(AuthUserDAO authUserDAO, UserDataUserDAO udUserDAO) {
        this.authUserDAO = authUserDAO;
        this.udUserDAO = udUserDAO;
    }

    public UserRepositoryImp() {
        this.authUserDAO = new AuthUserDAOHibernate();
        this.udUserDAO = new UserdataUserDAOHibernate();
    }

    @Override
    public void createUser(AuthUserEntity user) {
        authUserDAO.createUser(user);
        udUserDAO.createUser(fromAuthUser(user));
    }

    @Override
    public void removeUser(AuthUserEntity user) {
        UserDataEntity userInUd = udUserDAO.findByUsername(user.getUsername());
        udUserDAO.deleteUserByUsername(userInUd.getUsername());
        authUserDAO.deleteUser(user);
    }
    @Override
    public UserDataEntity findByUsername(AuthUserEntity user) {
        return udUserDAO.findByUsername(user.getUsername());
    }

    @Override
    public UserDataEntity findByUsername(String username) {
        return udUserDAO.findByUsername(username);
    }

    @Override
    public void removeAll() {
        udUserDAO.removeAll();
        authUserDAO.removeAll();
    }

    private UserDataEntity fromAuthUser(AuthUserEntity user) {
        UserDataEntity userdataUser = new UserDataEntity();
        userdataUser.setUsername(user.getUsername());
        return userdataUser;
    }
}

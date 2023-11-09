package guru.qa.rococo.db.repository;


import guru.qa.rococo.db.entity.auth.AuthUserEntity;
import guru.qa.rococo.db.entity.userdata.UserDataEntity;

public interface UserRepository {

    void createUser(AuthUserEntity user);

    void removeUser(AuthUserEntity user);

    UserDataEntity findByUsername(AuthUserEntity user);

    UserDataEntity findByUsername(String username);
}

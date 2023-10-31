package guru.qa.rococo.db.dao;


import guru.qa.rococo.db.model.userdata.UserDataEntity;

import java.util.UUID;

public interface UserDataUserDAO {

    void createUser(UserDataEntity user);

    void deleteUserById(UUID userId);

    void deleteUserByUsername(String username);

    UserDataEntity findByUsername(String username);

    void updateUser(UserDataEntity user);
}

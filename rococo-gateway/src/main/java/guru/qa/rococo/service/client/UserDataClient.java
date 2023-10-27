package guru.qa.rococo.service.client;

import guru.qa.rococo.model.UserJson;
import jakarta.annotation.Nonnull;


public interface UserDataClient {

    @Nonnull
    UserJson findByUsername(@Nonnull String username);
    @Nonnull
    UserJson update(@Nonnull UserJson userJson);

}

package guru.qa.rococo.api;

import guru.qa.rococo.api.interceptor.CodeInterceptor;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.pageable.MyPage;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.UUID;

public class MuseumClient extends RestService {

  public MuseumClient() {
    super(
        CFG.rococoGatewayUrl(),
        true,
        new CodeInterceptor()
    );
  }

  private final Museum museum = retrofit.create(Museum.class);

  @Step("Выполнить запрос api/museum?size={size}&page={page}")
  public MyPage<MuseumJson> getMuseum(Integer size, Integer page) throws IOException {
    return museum.getMuseum(size, page).execute().body();
  }

  @Step("Выполнить запрос api/museum/{uuid}")
  public MuseumJson getMuseumInfo(UUID uuid) throws IOException {
    return museum.getMuseumInfo(uuid).execute().body();
  }
}

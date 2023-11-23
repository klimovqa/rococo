package guru.qa.rococo.api;

import guru.qa.rococo.api.interceptor.CodeInterceptor;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.pageable.MyPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.junitplatform.AllurePostDiscoveryFilter;

import java.io.IOException;

public class GatewayClient extends RestService {

  public GatewayClient() {
    super(
        CFG.rococoGatewayUrl(),
        true,
        new CodeInterceptor()
    );
  }

  private final Gateway gateway = retrofit.create(Gateway.class);

  @Step("Выполнить запрос api/museum?size={size}&page={page}")
  public MyPage<MuseumJson> getMuseum(Integer size, Integer page) throws IOException {
    return gateway.getMuseum(size, page).execute().body();
  }
}

package guru.qa.rococo.api;

import guru.qa.rococo.api.interceptor.CodeInterceptor;
import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.api.pageable.MyPage;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.UUID;

public class PaintingClient extends RestService {

  public PaintingClient() {
    super(
        CFG.rococoGatewayUrl(),
        true,
        new CodeInterceptor()
    );
  }

  private final Painting painting = retrofit.create(Painting.class);

  @Step("Выполнить запрос api/painting?size={size}&page={page}")
  public MyPage<PaintingJson> getPainting(Integer size, Integer page) throws IOException {
    return painting.getPainting(size, page).execute().body();
  }

  @Step("Выполнить запрос api/painting/{uuid}")
  public PaintingJson getPaintingInfo(UUID uuid) throws IOException {
    return painting.getPaintingInfo(uuid).execute().body();
  }
}

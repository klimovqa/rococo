package guru.qa.rococo.api;

import guru.qa.rococo.api.interceptor.CodeInterceptor;
import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.api.pageable.MyPage;
import io.qameta.allure.Step;

import java.io.IOException;
import java.util.UUID;

public class ArtistClient extends RestService {

  public ArtistClient() {
    super(
        CFG.rococoGatewayUrl(),
        true,
        new CodeInterceptor()
    );
  }

  private final Artist artist = retrofit.create(Artist.class);

  @Step("Выполнить запрос api/artist?size={size}&page={page}")
  public MyPage<ArtistJson> getArtist(Integer size, Integer page) throws IOException {
    return artist.getArtist(size, page).execute().body();
  }

  @Step("Выполнить запрос api/artist/{uuid}")
  public ArtistJson getArtistInfo(UUID uuid) throws IOException {
    return artist.getArtistInfo(uuid).execute().body();
  }
}

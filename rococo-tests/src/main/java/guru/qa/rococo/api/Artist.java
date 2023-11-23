package guru.qa.rococo.api;

import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.pageable.MyPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.UUID;

public interface Artist {

  @GET("/api/artist")
  Call<MyPage<ArtistJson>> getArtist(
          @Query("size") Integer size,
          @Query("page") Integer page
  );
  @GET("/api/artist/{uuid}")
  Call<ArtistJson> getArtistInfo(
          @Path("uuid") UUID uuid
  );
}

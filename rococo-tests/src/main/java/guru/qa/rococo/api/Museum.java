package guru.qa.rococo.api;

import guru.qa.rococo.api.model.ArtistJson;
import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.api.pageable.MyPage;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.UUID;

public interface Museum {

  @GET("/api/museum")
  Call<MyPage<MuseumJson>> getMuseum(
          @Query("size") Integer size,
          @Query("page") Integer page
  );

  @GET("/api/museum/{uuid}")
  Call<MuseumJson> getMuseumInfo(
          @Path("uuid") UUID uuid
  );
}

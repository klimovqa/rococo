package guru.qa.rococo.api;

import guru.qa.rococo.api.model.PaintingJson;
import guru.qa.rococo.api.pageable.MyPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.UUID;

public interface Painting {
  @GET("/api/painting")
  Call<MyPage<PaintingJson>> getPainting(
          @Query("size") Integer size,
          @Query("page") Integer page
  );
  @GET("/api/painting/{uuid}")
  Call<PaintingJson> getPaintingInfo(
          @Path("uuid") UUID uuid
  );
}

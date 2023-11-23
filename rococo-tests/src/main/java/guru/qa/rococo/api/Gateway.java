package guru.qa.rococo.api;

import guru.qa.rococo.api.model.MuseumJson;
import guru.qa.rococo.api.pageable.MyPage;
import retrofit2.Call;
import retrofit2.http.*;

public interface Gateway {

  @GET("/api/museum")
  Call<MyPage<MuseumJson>> getMuseum(
          @Query("size") Integer size,
          @Query("page") Integer page
  );
}

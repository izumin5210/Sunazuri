package info.izumin.android.sunazuri.infrastructure.api;

import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by izumin on 5/21/2016 AD.
 */
public interface UsersApi {
    @GET("/v1/user")
    Single<AuthorizedUserEntity> fetch(
            @Query("access_token") String accessToken
    );
}

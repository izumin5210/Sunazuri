package info.izumin.android.sunazuri.infrastructure.api;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Completable;
import rx.Single;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface OauthApi {
    @POST("/oauth/token")
    Single<AccessTokenEntity> fetchToken(
            @Query("client_id")     String clientId,
            @Query("client_secret") String clientSecret,
            @Query("grant_type")    String grantType,
            @Query("redirect_uri")  String redirectUri,
            @Query("code")          String code
    );

    @POST("/oauth/revoke")
    Completable revoke(
            @Header("Authorization") String authorization
    );
}

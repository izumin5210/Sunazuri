package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import rx.Observable;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface OauthDataSource {
    Single<AccessTokenEntity> getToken(String code);
    Single<List<AccessTokenEntity>> getTokens();
    Observable<AccessTokenEntity> getCurrentToken();
}

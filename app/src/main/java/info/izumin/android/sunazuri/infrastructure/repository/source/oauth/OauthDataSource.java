package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import rx.Single;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface OauthDataSource {
    Single<AccessTokenEntity> getToken(String code);
}

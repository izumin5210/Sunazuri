package info.izumin.android.sunazuri.domain.repository;

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import rx.Single;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface OauthRepository {
    Single<AccessTokenEntity> getToken(String code);
}

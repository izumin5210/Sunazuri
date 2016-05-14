package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.domain.entity.AccessToken;
import rx.Single;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public interface OauthDataSource {
    Single<AccessToken> getToken(String code);
}

package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.api.OauthApi;
import info.izumin.android.sunazuri.infrastructure.api.UsersApi;
import info.izumin.android.sunazuri.infrastructure.cache.LoginCache;
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import info.izumin.android.sunazuri.infrastructure.util.Encryptor;
import rx.Observable;
import rx.Single;

import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class OauthRemoteDataSource implements OauthDataSource {
    public static final String TAG = OauthRemoteDataSource.class.getSimpleName();

    private final UsersApi usersApi;
    private final OauthApi oauthApi;
    private final OauthParams oauthParams;
    private final AccessTokenDao accessTokenDao;
    private final Encryptor encryptor;
    private final LoginCache loginCache;

    OauthRemoteDataSource(UsersApi usersApi,
                          OauthApi oauthApi,
                          OauthParams oauthParams,
                          AccessTokenDao accessTokenDao,
                          Encryptor encryptor,
                          LoginCache loginCache) {
        this.usersApi = usersApi;
        this.oauthApi = oauthApi;
        this.oauthParams = oauthParams;
        this.accessTokenDao = accessTokenDao;
        this.encryptor = encryptor;
        this.loginCache = loginCache;
    }

    @Override
    public Single<AccessTokenEntity> getToken(String code) {
        return oauthApi.fetchToken(
                oauthParams.clientId,
                oauthParams.clientSecret,
                oauthParams.grantType,
                oauthParams.redirectUri,
                code
        )
                .flatMap(token -> usersApi.fetch(token.accessToken)
                        .map(user -> {
                            token.user = user;
                            return token;
                        }))
                .map(token -> {
                    final String planeToken = token.accessToken;
                    token.accessToken = encryptor.encrypt(token.accessToken);
                    accessTokenDao.upsert(token);
                    token.accessToken = planeToken;
                    return token;
                })
                .doOnSuccess(token -> {
                    if (!loginCache.isUserCached()) {
                        loginCache.putUserId(token.user.id);
                    }
                });
    }

    @Override
    public Single<List<AccessTokenEntity>> getTokens() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<AccessTokenEntity> getCurrentToken() {
        throw new UnsupportedOperationException();
    }
}

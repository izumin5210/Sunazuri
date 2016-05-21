package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.api.OauthApi;
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import info.izumin.android.sunazuri.infrastructure.util.Encryptor;
import rx.Single;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class OauthRemoteDataSource implements OauthDataSource {
    public static final String TAG = OauthRemoteDataSource.class.getSimpleName();

    private final OauthApi oauthApi;
    private final OauthParams oauthParams;
    private final AccessTokenDao accessTokenDao;
    private final Encryptor encryptor;

    OauthRemoteDataSource(OauthApi oauthApi,
                          OauthParams oauthParams,
                          AccessTokenDao accessTokenDao,
                          Encryptor encryptor) {
        this.oauthApi = oauthApi;
        this.oauthParams = oauthParams;
        this.accessTokenDao = accessTokenDao;
        this.encryptor = encryptor;
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
                .map(token -> {
                    final String planToken = token.accessToken;
                    token.accessToken = encryptor.encrypt(token.accessToken);
                    accessTokenDao.upsert(token);
                    token.accessToken = planToken;
                    return token;
                });
    }
}

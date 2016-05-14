package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import com.facebook.crypto.Crypto;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import info.izumin.android.sunazuri.domain.entity.AccessToken;
import info.izumin.android.sunazuri.infrastructure.api.OauthApi;
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import rx.Single;
import rx.exceptions.Exceptions;

import java.io.IOException;

import static info.izumin.android.sunazuri.infrastructure.util.EncryptionUtils.encrypt;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class OauthRemoteDataSource implements OauthDataSource {
    public static final String TAG = OauthRemoteDataSource.class.getSimpleName();

    private final OauthApi oauthApi;
    private final OauthParams oauthParams;
    private final AccessTokenDao accessTokenDao;
    private final Crypto crypto;
    private final String keyStoreAlias;

    OauthRemoteDataSource(OauthApi oauthApi,
                          OauthParams oauthParams,
                          AccessTokenDao accessTokenDao,
                          Crypto crypto,
                          String keyStoreAlias) {
        this.oauthApi = oauthApi;
        this.oauthParams = oauthParams;
        this.accessTokenDao = accessTokenDao;
        this.crypto = crypto;
        this.keyStoreAlias = keyStoreAlias;
    }

    @Override
    public Single<AccessToken> getToken(String code) {
        return oauthApi.fetchToken(
                oauthParams.clientId,
                oauthParams.clientSecret,
                oauthParams.grantType,
                oauthParams.redirectUri,
                code
        )
                .flatMap(token -> {
                    final String planToken = token.accessToken;
                    try {
                        token.accessToken = encrypt(crypto, keyStoreAlias, token.accessToken);
                        return accessTokenDao.insert(token).map(t -> {
                            t.accessToken = planToken;
                            return t;
                        });
                    } catch (IOException | KeyChainException | CryptoInitializationException e) {
                        e.printStackTrace();
                        throw Exceptions.propagate(e);
                    }
                });
    }
}

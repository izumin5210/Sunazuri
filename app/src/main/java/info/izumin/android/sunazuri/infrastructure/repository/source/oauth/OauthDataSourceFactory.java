package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import com.facebook.crypto.Crypto;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import info.izumin.android.sunazuri.infrastructure.api.OauthApi;
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao;
import info.izumin.android.sunazuri.infrastructure.qualifier.KeyStoreAlias;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
public class OauthDataSourceFactory {
    public static final String TAG = OauthDataSourceFactory.class.getSimpleName();

    private final OauthApi oauthApi;
    private final OauthParams oauthParams;
    private final AccessTokenDao accessTokenDao;
    private final Crypto crypto;
    private final String keyStoreAlias;

    @Inject
    OauthDataSourceFactory(OauthApi oauthApi,
                           OauthParams oauthParams,
                           AccessTokenDao accessTokenDao,
                           Crypto crypto,
                           @KeyStoreAlias String keyStoreAlias) {
        this.oauthApi = oauthApi;
        this.oauthParams = oauthParams;
        this.accessTokenDao = accessTokenDao;
        this.crypto = crypto;
        this.keyStoreAlias = keyStoreAlias;
    }

    public OauthDataSource createRemoteDataSource() {
        return new OauthRemoteDataSource(oauthApi, oauthParams, accessTokenDao, crypto, keyStoreAlias);
    }
}

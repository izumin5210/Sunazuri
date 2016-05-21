package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import info.izumin.android.sunazuri.infrastructure.api.OauthApi;
import info.izumin.android.sunazuri.infrastructure.api.UsersApi;
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import info.izumin.android.sunazuri.infrastructure.util.Encryptor;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
public class OauthDataSourceFactory {
    public static final String TAG = OauthDataSourceFactory.class.getSimpleName();

    private final UsersApi usersApi;
    private final OauthApi oauthApi;
    private final OauthParams oauthParams;
    private final AccessTokenDao accessTokenDao;
    private final Encryptor encryptor;

    @Inject
    OauthDataSourceFactory(UsersApi usersApi,
                           OauthApi oauthApi,
                           OauthParams oauthParams,
                           AccessTokenDao accessTokenDao,
                           Encryptor encryptor) {
        this.usersApi = usersApi;
        this.oauthApi = oauthApi;
        this.oauthParams = oauthParams;
        this.accessTokenDao = accessTokenDao;
        this.encryptor = encryptor;
    }

    public OauthDataSource createRemoteDataSource() {
        return new OauthRemoteDataSource(usersApi, oauthApi, oauthParams, accessTokenDao, encryptor);
    }
}

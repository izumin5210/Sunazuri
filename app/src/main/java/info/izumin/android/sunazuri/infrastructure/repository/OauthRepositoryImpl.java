package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.AuthorizedUserMapper;
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthDataSourceFactory;
import rx.Single;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
class OauthRepositoryImpl implements OauthRepository {
    public static final String TAG = OauthRepositoryImpl.class.getSimpleName();

    private final OauthDataSourceFactory dataSourceFactory;
    private final AuthorizedUserMapper authorizedUserMapper;

    @Inject
    OauthRepositoryImpl(OauthDataSourceFactory dataSourceFactory,
                        AuthorizedUserMapper authorizedUserMapper) {
        this.dataSourceFactory = dataSourceFactory;
        this.authorizedUserMapper = authorizedUserMapper;
    }

    @Override
    public Single<AuthorizedUser> getToken(String code) {
        return dataSourceFactory.createRemoteDataSource()
                .getToken(code)
                .map(authorizedUserMapper::transform);
    }
}

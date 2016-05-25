package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.AuthorizedUserMapper;
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthDataSourceFactory;
import rx.Observable;
import rx.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by izumin on 5/24/2016 AD.
 */
@Singleton
class UsersRepositoryImpl implements UsersRepository {
    public static final String TAG = UsersRepositoryImpl.class.getSimpleName();

    private final OauthDataSourceFactory oauthDataSourceFactory;
    private final AuthorizedUserMapper authorizedUserMapper;

    @Inject
    UsersRepositoryImpl(OauthDataSourceFactory oauthDataSourceFactory,
                        AuthorizedUserMapper authorizedUserMapper) {
        this.oauthDataSourceFactory = oauthDataSourceFactory;
        this.authorizedUserMapper = authorizedUserMapper;
    }

    @Override
    public Observable<AuthorizedUser> getCurrentUser() {
        return oauthDataSourceFactory.createLocalDataSource()
                .getCurrentToken()
                .map(authorizedUserMapper::transform);
    }

    @Override
    public Single<List<AuthorizedUser>> getAuthorizedUsers() {
        return oauthDataSourceFactory.createLocalDataSource()
                .getTokens()
                .map(authorizedUserMapper::transform);
    }
}

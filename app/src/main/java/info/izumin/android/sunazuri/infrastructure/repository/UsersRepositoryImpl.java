package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import info.izumin.android.sunazuri.domain.entity.LoginInfo;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.AuthorizedUserMapper;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.TeamMapper;
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthDataSourceFactory;
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsDataSourceFactory;
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
    private final TeamsDataSourceFactory teamsDataSourceFactory;
    private final AuthorizedUserMapper authorizedUserMapper;
    private final TeamMapper teamMapper;

    @Inject
    UsersRepositoryImpl(OauthDataSourceFactory oauthDataSourceFactory,
                        TeamsDataSourceFactory teamsDataSourceFactory,
                        AuthorizedUserMapper authorizedUserMapper,
                        TeamMapper teamMapper) {
        this.oauthDataSourceFactory = oauthDataSourceFactory;
        this.teamsDataSourceFactory = teamsDataSourceFactory;
        this.authorizedUserMapper = authorizedUserMapper;
        this.teamMapper = teamMapper;
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

    @Override
    public Observable<LoginInfo> getLoginInfo() {
        return Observable.zip(
                oauthDataSourceFactory.createLocalDataSource()
                        .getCurrentToken()
                        .defaultIfEmpty(null)
                        .map(authorizedUserMapper::transform),
                teamsDataSourceFactory.createLocalDataSource()
                        .getCurrentTeam()
                        .defaultIfEmpty(null)
                        .map(teamMapper::transform),
                (user, team) -> {
                    if (user != null && team != null) {
                        return new LoginInfo(user, team);
                    } else {
                        return null;
                    }
                }
        ).filter(info -> info != null);
    }
}

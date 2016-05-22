package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;
import info.izumin.android.sunazuri.domain.entity.Team;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.AuthorizedUserMapper;
import info.izumin.android.sunazuri.infrastructure.entity.mapper.TeamMapper;
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsDataSourceFactory;
import rx.Single;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class TeamsRepositoryImpl implements TeamsRepository {
    public static final String TAG = TeamsRepositoryImpl.class.getSimpleName();

    private final TeamsDataSourceFactory dataSourceFactory;
    private final AuthorizedUserMapper authorizedUserMapper;
    private final TeamMapper teamMapper;

    @Inject
    TeamsRepositoryImpl(TeamsDataSourceFactory dataSourceFactory,
                        AuthorizedUserMapper authorizedUserMapper,
                        TeamMapper teamMapper) {
        this.dataSourceFactory = dataSourceFactory;
        this.authorizedUserMapper = authorizedUserMapper;
        this.teamMapper = teamMapper;
    }

    @Override
    public Single<List<Team>> get(AuthorizedUser user) {
        return dataSourceFactory.createRemoteDataSource()
                .getTeams(authorizedUserMapper.transform(user))
                .map(teamMapper::transformFromTeamStatsEntities);
    }
}

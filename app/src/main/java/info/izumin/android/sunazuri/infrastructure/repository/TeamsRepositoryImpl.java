package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.domain.entity.Team;
import info.izumin.android.sunazuri.domain.entity.TeamStats;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsDataSourceFactory;
import rx.Observable;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
class TeamsRepositoryImpl implements TeamsRepository {
    public static final String TAG = TeamsRepositoryImpl.class.getSimpleName();

    private final TeamsDataSourceFactory dataSourceFactory;

    @Inject
    TeamsRepositoryImpl(TeamsDataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    @Override
    public Observable<List<Team>> get() {
        return null;
    }

    @Override
    public Observable<Team> get(String teamName) {
        return null;
    }

    @Override
    public Observable<TeamStats> getStats(String teamName) {
        return null;
    }
}
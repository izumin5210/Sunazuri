package info.izumin.android.sunazuri.infrastructure.repository;

import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity;
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity;
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
    public Observable<List<TeamEntity>> get() {
        return null;
    }

    @Override
    public Observable<TeamEntity> get(String teamName) {
        return null;
    }

    @Override
    public Observable<TeamStatsEntity> getStats(String teamName) {
        return null;
    }
}

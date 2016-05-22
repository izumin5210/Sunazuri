package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi;
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
public class TeamsDataSourceFactory {
    public static final String TAG = TeamsDataSourceFactory.class.getSimpleName();

    private final TeamsApi teamsApi;
    private final TeamsDao teamsDao;

    @Inject
    TeamsDataSourceFactory(TeamsApi teamsApi, TeamsDao teamsDao) {
        this.teamsApi = teamsApi;
        this.teamsDao = teamsDao;
    }

    public TeamsDataSource createRemoteDataSource() {
        return new TeamsRemoteDataSource(teamsApi, teamsDao);
    }

    public TeamsDataSource createLocalDataSource() {
        return new TeamsLocalDataSource(teamsDao);
    }
}

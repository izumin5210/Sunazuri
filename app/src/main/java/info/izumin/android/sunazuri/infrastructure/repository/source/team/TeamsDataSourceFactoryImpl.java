package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi;
import info.izumin.android.sunazuri.infrastructure.cache.LoginCache;
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
class TeamsDataSourceFactoryImpl implements TeamsDataSourceFactory {
    public static final String TAG = TeamsDataSourceFactoryImpl.class.getSimpleName();

    private final TeamsApi teamsApi;
    private final TeamsDao teamsDao;
    private final LoginCache loginCache;

    @Inject
    TeamsDataSourceFactoryImpl(TeamsApi teamsApi, TeamsDao teamsDao, LoginCache loginCache) {
        this.teamsApi = teamsApi;
        this.teamsDao = teamsDao;
        this.loginCache = loginCache;
    }

    @Override
    public TeamsDataSource createLocalDataSource() {
        return new TeamsLocalDataSource(teamsDao, loginCache);
    }

    @Override
    public TeamsDataSource createRemoteDataSource() {
        return new TeamsRemoteDataSource(teamsApi, teamsDao, loginCache);
    }
}

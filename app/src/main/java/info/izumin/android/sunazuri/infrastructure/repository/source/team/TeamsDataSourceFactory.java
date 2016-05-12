package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
public class TeamsDataSourceFactory {
    public static final String TAG = TeamsDataSourceFactory.class.getSimpleName();

    private final TeamsApi teamsApi;

    @Inject
    TeamsDataSourceFactory(TeamsApi teamsApi) {
        this.teamsApi = teamsApi;
    }

    public TeamsDataSource createRemoteDataSource() {
        return new TeamsRemoteDataSource(teamsApi);
    }
}

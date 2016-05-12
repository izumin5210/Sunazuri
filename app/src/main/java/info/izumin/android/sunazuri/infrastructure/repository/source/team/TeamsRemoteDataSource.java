package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
class TeamsRemoteDataSource implements TeamsDataSource {
    public static final String TAG = TeamsRemoteDataSource.class.getSimpleName();

    private final TeamsApi api;

    @Inject
    TeamsRemoteDataSource(TeamsApi api) {
        this.api = api;
    }
}

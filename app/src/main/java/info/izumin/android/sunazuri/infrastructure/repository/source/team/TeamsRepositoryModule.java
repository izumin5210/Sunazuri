package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@Module
public class TeamsRepositoryModule {
    public static final String TAG = TeamsRepositoryModule.class.getSimpleName();

    @Provides
    @Singleton
    TeamsRepository teamsRepository(TeamsRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    TeamsDataSourceFactory teamsDataSourceFactory(TeamsDataSourceFactoryImpl dataSourceFactory) {
        return dataSourceFactory;
    }
}

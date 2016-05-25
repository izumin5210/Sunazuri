package info.izumin.android.sunazuri.infrastructure.repository;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Module
public class RepositoryModule {
    public static final String TAG = RepositoryModule.class.getSimpleName();

    @Provides
    @Singleton
    TeamsRepository teamsRepository(TeamsRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    OauthRepository oauthRepository(OauthRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    UsersRepository usersRepository(UsersRepositoryImpl repo) {
        return repo;
    }
}

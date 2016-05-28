package info.izumin.android.sunazuri.infrastructure.repository;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthRepositoryModule;
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsRepositoryModule;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Module(
        includes = {
                OauthRepositoryModule.class,
                TeamsRepositoryModule.class
        }
)
public class RepositoryModule {
    public static final String TAG = RepositoryModule.class.getSimpleName();

    @Provides
    @Singleton
    UsersRepository usersRepository(UsersRepositoryImpl repo) {
        return repo;
    }
}

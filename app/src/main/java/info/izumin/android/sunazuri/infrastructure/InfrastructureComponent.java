package info.izumin.android.sunazuri.infrastructure;

import dagger.Component;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;
import info.izumin.android.sunazuri.domain.repository.TeamsRepository;
import info.izumin.android.sunazuri.domain.repository.UsersRepository;
import info.izumin.android.sunazuri.infrastructure.api.ApiModule;
import info.izumin.android.sunazuri.infrastructure.cache.CacheModule;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import info.izumin.android.sunazuri.infrastructure.repository.RepositoryModule;
import okhttp3.OkHttpClient;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Singleton
@Component(
        modules = {
                InfrastructureModule.class,
                RepositoryModule.class,
                HttpClientModule.class,
                ApiModule.class,
                CacheModule.class
        }
)
public interface InfrastructureComponent {
    TeamsRepository teamsRepository();
    OauthRepository oauthRepository();
    UsersRepository usersRepository();

    OauthParams oauthParams();

    // for Picasso
    OkHttpClient okHttpClient();
}

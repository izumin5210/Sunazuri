package info.izumin.android.sunazuri.infrastructure.repository.source.oauth;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.domain.repository.OauthRepository;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@Module
public class OauthRepositoryModule {
    public static final String TAG = OauthRepositoryModule.class.getSimpleName();

    @Provides
    @Singleton
    OauthRepository oauthRepository(OauthRepositoryImpl repo) {
        return repo;
    }

    @Provides
    @Singleton
    OauthDataSourceFactory oauthDataSourceFactory(OauthDataSourceFactoryImpl dataSourceFactory) {
        return dataSourceFactory;
    }
}

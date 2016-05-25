package info.izumin.android.sunazuri.infrastructure.cache;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Created by izumin on 5/25/2016 AD.
 */
@Module
public class CacheModule {
    public static final String TAG = CacheModule.class.getSimpleName();

    @Provides
    @Singleton
    LoginCache loginCache(LoginCacheImpl cache) {
        return cache;
    }
}

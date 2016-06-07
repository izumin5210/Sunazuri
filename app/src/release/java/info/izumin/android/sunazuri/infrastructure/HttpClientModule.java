package info.izumin.android.sunazuri.infrastructure;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;

/**
 * Created by izumin on 6/7/2016 AD.
 */
@Module
public class HttpClientModule {
    public static final String TAG = HttpClientModule.class.getSimpleName();

    @Provides
    @Singleton
    OkHttpClient httpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}

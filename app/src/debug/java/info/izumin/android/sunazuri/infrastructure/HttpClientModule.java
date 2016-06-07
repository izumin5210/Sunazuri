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
    OkHttpClient httpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new StethoInterceptor())
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}

package info.izumin.android.sunazuri.infrastructure.api;

import com.github.gfx.static_gson.StaticGsonTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Module
public class ApiModule {
    public static final String TAG = ApiModule.class.getSimpleName();

    private final String apiEndpoint;
    private final OauthParams oauthParams;
    private final List<String> responseEnvelopKeys;

    public ApiModule(String apiEndpoint, OauthParams oauthParams, List<String> responseEnvelopKeys) {
        this.apiEndpoint = apiEndpoint;
        this.oauthParams = oauthParams;
        this.responseEnvelopKeys = responseEnvelopKeys;
    }

    @Provides
    @Singleton
    OkHttpClient httpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor loggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    EnvelopeTypeAdapterFactory envelopeTypeAdapterFactory() {
        return new EnvelopeTypeAdapterFactory(responseEnvelopKeys);
    }

    @Provides
    @Singleton
    Gson gson(EnvelopeTypeAdapterFactory envelopeTypeAdapterFactory) {
        return new GsonBuilder()
                .registerTypeAdapterFactory(StaticGsonTypeAdapterFactory.newInstance())
                .registerTypeAdapterFactory(envelopeTypeAdapterFactory)
                .create();
    }

    @Provides
    @Singleton
    Retrofit retrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(apiEndpoint)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    OauthApi oauthApi(Retrofit retrofit) {
        return retrofit.create(OauthApi.class);
    }

    @Provides
    @Singleton
    TeamsApi teamsApi(Retrofit retrofit) {
        return retrofit.create(TeamsApi.class);
    }

    @Provides
    @Singleton
    UsersApi usersApi(Retrofit retrofit) {
        return retrofit.create(UsersApi.class);
    }

    @Provides
    @Singleton
    OauthParams oauthParams() {
        return oauthParams;
    }
}

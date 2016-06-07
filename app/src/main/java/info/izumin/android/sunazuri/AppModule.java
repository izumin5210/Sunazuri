package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@Module
public class AppModule {
    public static final String TAG = AppModule.class.getSimpleName();

    private final Application app;

    AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @AppScope
    Context context() {
        return app.getApplicationContext();
    }

    @Provides
    @AppScope
    Resources resources() {
        return app.getResources();
    }

    @Provides
    @AppScope
    Picasso picasso(Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }
}

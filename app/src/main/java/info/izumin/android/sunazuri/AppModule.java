package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import dagger.Module;
import dagger.Provides;

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
}

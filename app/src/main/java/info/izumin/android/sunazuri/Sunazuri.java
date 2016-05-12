package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public class Sunazuri extends Application {
    public static final String TAG = Sunazuri.class.getSimpleName();

    private AppComponent component;

    public static Sunazuri get(Context context) {
        return (Sunazuri) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupComponent();
    }

    public AppComponent getComponent() {
        return component;
    }

    private void setupComponent() {
        component = DaggerAppComponent.builder()
                .build();
    }
}

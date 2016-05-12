package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;
import info.izumin.android.sunazuri.data.DaggerDataComponent;
import info.izumin.android.sunazuri.data.DataComponent;
import info.izumin.android.sunazuri.infrastructure.DaggerInfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.InfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.api.ApiModule;

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
                .dataComponent(getDataComponent())
                .build();
    }

    private DataComponent getDataComponent() {
        return DaggerDataComponent.builder()
                .infrastructureComponent(getInfrastructureComponent())
                .build();
    }

    private InfrastructureComponent getInfrastructureComponent() {
        return DaggerInfrastructureComponent.builder()
                .apiModule(new ApiModule(BuildConfig.ESA_API_ENDPOINT))
                .build();
    }
}

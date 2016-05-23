package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;
import info.izumin.android.sunazuri.data.DaggerDataComponent;
import info.izumin.android.sunazuri.data.DataComponent;
import info.izumin.android.sunazuri.infrastructure.DaggerInfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.InfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.InfrastructureModule;
import info.izumin.android.sunazuri.infrastructure.api.ApiModule;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

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
        setupTimber();
    }

    public AppComponent getComponent() {
        return component;
    }

    private void setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
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
                .infrastructureModule(new InfrastructureModule(this, BuildConfig.KEYSTORE_ALIAS))
                .apiModule(new ApiModule(BuildConfig.ESA_API_ENDPOINT, getOauthParams(), getResponseEnvelopeKeys()))
                .build();
    }

    private OauthParams getOauthParams() {
        return new OauthParams(
                BuildConfig.ESA_API_ENDPOINT,
                BuildConfig.ESA_CLIENT_ID,
                BuildConfig.ESA_CLIENT_SECRET,
                BuildConfig.ESA_CALLBACK_URI,
                BuildConfig.ESA_OAUTH_SCOPE,
                "code",
                "/oauth/authorize",
                BuildConfig.ESA_OAUTH_GRANT_TYPE
        );
    }

    private List<String> getResponseEnvelopeKeys() {
        return new ArrayList<String>(){{
            add("teams");
        }};
    }
}

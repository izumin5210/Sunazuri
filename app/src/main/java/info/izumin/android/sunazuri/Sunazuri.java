package info.izumin.android.sunazuri;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;
import info.izumin.android.sunazuri.data.DaggerDataComponent;
import info.izumin.android.sunazuri.data.DataComponent;
import info.izumin.android.sunazuri.data.action.user.UserActionCreator;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.infrastructure.DaggerInfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.InfrastructureComponent;
import info.izumin.android.sunazuri.infrastructure.InfrastructureModule;
import info.izumin.android.sunazuri.infrastructure.api.ApiModule;
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by izumin on 5/13/2016 AD.
 */
public class Sunazuri extends Application {
    public static final String TAG = Sunazuri.class.getSimpleName();

    public static Sunazuri get(Context context) {
        return (Sunazuri) context.getApplicationContext();
    }

    @Inject RootStore store;
    @Inject UserActionCreator userActionCreator;

    private AppComponent component;
    private CompositeSubscription subscriptions;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        setupComponent();
        setupTimber();
        initialize();
    }

    @Override
    public void onTerminate() {
        subscriptions.clear();
        super.onTerminate();
    }

    public AppComponent getComponent() {
        return component;
    }

    private void initialize() {
        subscriptions = new CompositeSubscription();
        subscriptions.add(
                store.dispatch(userActionCreator.createLoadLoginInfoAction())
                        .subscribeOn(Schedulers.io())
                        .subscribe()
        );
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
        component.inject(this);
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

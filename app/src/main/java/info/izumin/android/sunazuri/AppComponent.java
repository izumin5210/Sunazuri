package info.izumin.android.sunazuri;

import dagger.Component;
import info.izumin.android.sunazuri.data.DataComponent;
import info.izumin.android.sunazuri.presentation.activity.main.MainComponent;
import info.izumin.android.sunazuri.presentation.activity.main.MainModule;
import info.izumin.android.sunazuri.presentation.activity.oauth.OauthActivity;

/**
 * Created by izumin on 5/13/2016 AD.
 */
@AppScope
@Component(
        dependencies = {
                DataComponent.class
        },
        modules = {
                AppModule.class
        }
)
public interface AppComponent {
    void inject(Sunazuri sunazuri);
    void inject(OauthActivity activity);

    MainComponent plus(MainModule module);
}

package info.izumin.android.sunazuri.presentation.activity.main;

import dagger.Subcomponent;
import info.izumin.android.sunazuri.presentation.fragment.welcome.WelcomeComponent;
import info.izumin.android.sunazuri.presentation.fragment.welcome.WelcomeModule;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@MainScope
@Subcomponent(
        modules = MainModule.class
)
public interface MainComponent {
    void inject(MainActivity activity);

    WelcomeComponent plus(WelcomeModule module);
}

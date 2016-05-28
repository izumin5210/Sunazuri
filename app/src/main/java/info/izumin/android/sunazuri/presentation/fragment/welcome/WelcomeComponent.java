package info.izumin.android.sunazuri.presentation.fragment.welcome;

import dagger.Subcomponent;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@WelcomeScope
@Subcomponent(
        modules = WelcomeModule.class
)
public interface WelcomeComponent {
    void inject(WelcomeFragment fragment);
}

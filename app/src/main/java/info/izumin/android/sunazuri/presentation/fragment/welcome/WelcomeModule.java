package info.izumin.android.sunazuri.presentation.fragment.welcome;

import dagger.Module;
import dagger.Provides;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@Module
public class WelcomeModule {
    public static final String TAG = WelcomeModule.class.getSimpleName();

    private final WelcomeContract.View view;

    WelcomeModule(WelcomeContract.View view) {
        this.view = view;
    }

    @Provides
    @WelcomeScope
    WelcomeContract.View view() {
        return view;
    }

    @Provides
    @WelcomeScope
    WelcomeContract.Presenter presenter(WelcomePresenter presenter) {
        return presenter;
    }

    @Provides
    @WelcomeScope
    WelcomeContract.Controller controller(WelcomeController controller) {
        return controller;
    }
}

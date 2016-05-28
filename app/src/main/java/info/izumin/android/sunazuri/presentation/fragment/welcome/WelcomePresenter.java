package info.izumin.android.sunazuri.presentation.fragment.welcome;

import javax.inject.Inject;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@WelcomeScope
class WelcomePresenter implements WelcomeContract.Presenter {
    public static final String TAG = WelcomePresenter.class.getSimpleName();

    private final WelcomeContract.View view;

    @Inject
    WelcomePresenter(WelcomeContract.View view) {
        this.view = view;
    }

    @Override
    public void onResume() { }

    @Override
    public void onPause() { }

    @Override
    public void onStop() { }

    @Override
    public void onDestroy() { }

    @Override
    public void openOauth() {
        view.showOauthUi();
    }

    @Override
    public void openPosts() {
        view.showPostsUi();
    }
}

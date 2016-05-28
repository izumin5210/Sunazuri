package info.izumin.android.sunazuri.presentation.fragment.welcome;

import android.app.Activity;
import android.view.View;
import info.izumin.android.sunazuri.RequestCode;
import onactivityresult.OnActivityResult;

import javax.inject.Inject;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@WelcomeScope
class WelcomeController implements WelcomeContract.Controller {
    public static final String TAG = WelcomeController.class.getSimpleName();

    private final WelcomeContract.Presenter presenter;

    @Inject
    WelcomeController(WelcomeContract.Presenter presenter) {
        this.presenter = presenter;
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
    public void onBtnLoginClick(View _v) {
        presenter.openOauth();
    }

    @Override
    @OnActivityResult(requestCode = RequestCode.OAUTH, resultCodes = Activity.RESULT_OK)
    public void onOauthSuccess() {
        presenter.openPosts();
    }

    @Override
    @OnActivityResult(requestCode = RequestCode.OAUTH, resultCodes = Activity.RESULT_CANCELED)
    public void onOauthDeny() {
        // TODO: Not yet implemented.
    }
}

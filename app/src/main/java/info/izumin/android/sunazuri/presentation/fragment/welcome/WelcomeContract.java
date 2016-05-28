package info.izumin.android.sunazuri.presentation.fragment.welcome;

import info.izumin.android.sunazuri.presentation.common.BaseController;
import info.izumin.android.sunazuri.presentation.common.BasePresenter;

/**
 * Created by izumin on 5/28/2016 AD.
 */
public interface WelcomeContract {
    interface View {
        void showOauthUi();
        void showPostsUi();
    }

    interface Controller extends BaseController {
        void onBtnLoginClick(android.view.View view);
        void onOauthSuccess();
        void onOauthDeny();
    }

    interface Presenter extends BasePresenter {
        void openOauth();
        void openPosts();
    }
}

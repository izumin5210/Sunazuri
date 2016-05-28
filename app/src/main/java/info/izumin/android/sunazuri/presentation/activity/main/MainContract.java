package info.izumin.android.sunazuri.presentation.activity.main;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by izumin on 5/28/2016 AD.
 */
public interface MainContract {
    interface View {
        void setFragment(Fragment fragment);
        Context getActivityContext();
        MainComponent getMainComponent();
    }
}

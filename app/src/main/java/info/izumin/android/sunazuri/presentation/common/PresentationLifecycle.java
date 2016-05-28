package info.izumin.android.sunazuri.presentation.common;

/**
 * Created by izumin on 5/28/2016 AD.
 */
interface PresentationLifecycle {
    void onResume();
    void onPause();
    void onStop();
    void onDestroy();
}

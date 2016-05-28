package info.izumin.android.sunazuri.presentation.activity.main;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.presentation.qualifier.ActivityContext;

/**
 * Created by izumin on 5/28/2016 AD.
 */
@Module
public class MainModule {
    public static final String TAG = MainModule.class.getSimpleName();

    private final MainContract.View view;

    MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    @MainScope
    @ActivityContext
    Context activityContext() {
        return view.getActivityContext();
    }

    @Provides
    @MainScope
    MainContract.View view() {
        return view;
    }
}

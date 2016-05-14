package info.izumin.android.sunazuri.data;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.sunazuri.data.reducer.AccessTokenReducer;
import info.izumin.android.sunazuri.domain.DroiduxRootStore;
import info.izumin.android.sunazuri.domain.RootStore;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Module
public class DataModule {
    public static final String TAG = DataModule.class.getSimpleName();

    @Provides
    @DataScope
    RootStore rootStore() {
        return DroiduxRootStore.builder()
                .setReducer(new AccessTokenReducer())
                .build();
    }
}

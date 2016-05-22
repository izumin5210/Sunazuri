package info.izumin.android.sunazuri.data;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.droidux.thunk.ThunkMiddleware;
import info.izumin.android.sunazuri.data.reducer.AuthorizedUsersReducer;
import info.izumin.android.sunazuri.domain.DroiduxRootStore;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.domain.model.AuthorizedUsers;

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
                .setReducer(new AuthorizedUsersReducer(), new AuthorizedUsers())
                .addMiddleware(new ThunkMiddleware())
                .build();
    }
}

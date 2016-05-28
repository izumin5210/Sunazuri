package info.izumin.android.sunazuri.data;

import dagger.Module;
import dagger.Provides;
import info.izumin.android.droidux.thunk.ThunkMiddleware;
import info.izumin.android.sunazuri.data.reducer.AuthorizedUsersReducer;
import info.izumin.android.sunazuri.data.reducer.LoginInfoReducer;
import info.izumin.android.sunazuri.data.reducer.TeamsReducer;
import info.izumin.android.sunazuri.domain.DroiduxRootStore;
import info.izumin.android.sunazuri.domain.RootStore;
import info.izumin.android.sunazuri.domain.model.AuthorizedUsers;
import info.izumin.android.sunazuri.domain.model.Teams;

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
                .setReducer(new TeamsReducer(), new Teams())
                .setReducer(new LoginInfoReducer())
                .addMiddleware(new ThunkMiddleware())
                .build();
    }
}

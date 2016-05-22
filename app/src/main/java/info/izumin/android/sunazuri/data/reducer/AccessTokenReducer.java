package info.izumin.android.sunazuri.data.reducer;

import info.izumin.android.droidux.annotation.Dispatchable;
import info.izumin.android.droidux.annotation.Reducer;
import info.izumin.android.sunazuri.data.action.SetAccessTokenAction;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Reducer(AccessTokenEntity.class)
public class AccessTokenReducer {
    public static final String TAG = AccessTokenReducer.class.getSimpleName();

    @Dispatchable(SetAccessTokenAction.class)
    public AccessTokenEntity set(SetAccessTokenAction action) {
        return action.getToken();
    }
}

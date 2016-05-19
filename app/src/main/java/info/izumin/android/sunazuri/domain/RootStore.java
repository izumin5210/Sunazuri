package info.izumin.android.sunazuri.domain;

import info.izumin.android.droidux.BaseStore;
import info.izumin.android.droidux.annotation.Store;
import info.izumin.android.sunazuri.data.reducer.AccessTokenReducer;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Store({
        AccessTokenReducer.class
})
public interface RootStore extends BaseStore {
    AccessTokenEntity getAccessToken();
}

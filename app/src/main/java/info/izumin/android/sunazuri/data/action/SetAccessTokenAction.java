package info.izumin.android.sunazuri.data.action;

import info.izumin.android.droidux.Action;
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity;

/**
 * Created by izumin on 5/14/2016 AD.
 */
public class SetAccessTokenAction implements Action {
    public static final String TAG = SetAccessTokenAction.class.getSimpleName();

    private final AccessTokenEntity token;

    public SetAccessTokenAction(AccessTokenEntity token) {
        this.token = token;
    }

    public AccessTokenEntity getToken() {
        return token;
    }
}

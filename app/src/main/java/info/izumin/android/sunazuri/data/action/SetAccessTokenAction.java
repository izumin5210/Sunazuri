package info.izumin.android.sunazuri.data.action;

import info.izumin.android.droidux.Action;
import info.izumin.android.sunazuri.domain.entity.AccessToken;

/**
 * Created by izumin on 5/14/2016 AD.
 */
public class SetAccessTokenAction implements Action {
    public static final String TAG = SetAccessTokenAction.class.getSimpleName();

    private final AccessToken token;

    public SetAccessTokenAction(AccessToken token) {
        this.token = token;
    }

    public AccessToken getToken() {
        return token;
    }
}

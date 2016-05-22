package info.izumin.android.sunazuri.domain.model;

import info.izumin.android.sunazuri.domain.entity.AuthorizedUser;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by izumin on 5/22/2016 AD.
 */
public class AuthorizedUsers extends ArrayList<AuthorizedUser> {
    public static final String TAG = AuthorizedUsers.class.getSimpleName();

    public AuthorizedUsers() {
        super();
    }

    public AuthorizedUsers(Collection<? extends AuthorizedUser> collection) {
        super(collection);
    }
}

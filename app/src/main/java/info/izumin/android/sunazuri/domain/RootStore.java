package info.izumin.android.sunazuri.domain;

import info.izumin.android.droidux.BaseStore;
import info.izumin.android.droidux.annotation.Store;
import info.izumin.android.sunazuri.data.reducer.AuthorizedUsersReducer;
import info.izumin.android.sunazuri.data.reducer.LoginInfoReducer;
import info.izumin.android.sunazuri.data.reducer.TeamsReducer;
import info.izumin.android.sunazuri.domain.entity.LoginInfo;
import info.izumin.android.sunazuri.domain.model.AuthorizedUsers;
import info.izumin.android.sunazuri.domain.model.Teams;

/**
 * Created by izumin on 5/14/2016 AD.
 */
@Store({
        AuthorizedUsersReducer.class,
        LoginInfoReducer.class,
        TeamsReducer.class
})
public interface RootStore extends BaseStore {
    AuthorizedUsers getAuthorizedUsers();
    Teams getTeams();
    LoginInfo getLoginInfo();
}

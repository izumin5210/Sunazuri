package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.droidux.Action
import info.izumin.android.droidux.annotation.Dispatchable
import info.izumin.android.droidux.annotation.Reducer;
import info.izumin.android.sunazuri.domain.model.AuthorizedUsers;

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Reducer(AuthorizedUsers::class)
class AuthorizedUsersReducer {
    @Dispatchable(AddAuthorizedUserAction::class)
    fun add(state: AuthorizedUsers, action: AddAuthorizedUserAction): AuthorizedUsers {
        val newState = AuthorizedUsers(state)
        newState.add(action.value.user)
        return newState
    }
}

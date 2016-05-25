package info.izumin.android.sunazuri.data.reducer;

import info.izumin.android.droidux.annotation.Dispatchable
import info.izumin.android.droidux.annotation.Reducer
import info.izumin.android.sunazuri.data.action.user.SetCurrentUserAction
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser

/**
 * Created by izumin on 5/25/2016 AD.
 */
@Reducer(AuthorizedUser::class)
class CurrentUserReducer {
    @Dispatchable(SetCurrentUserAction::class)
    fun set(action: SetCurrentUserAction): AuthorizedUser? {
        return action.value.user
    }
}

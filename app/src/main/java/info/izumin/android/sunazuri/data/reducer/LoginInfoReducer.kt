package info.izumin.android.sunazuri.data.reducer;

import info.izumin.android.droidux.annotation.Dispatchable
import info.izumin.android.droidux.annotation.Reducer
import info.izumin.android.sunazuri.data.action.user.SetLoginInfoAction
import info.izumin.android.sunazuri.domain.entity.LoginInfo

/**
 * Created by izumin on 5/25/2016 AD.
 */
@Reducer(LoginInfo::class)
class LoginInfoReducer {
    @Dispatchable(SetLoginInfoAction::class)
    fun set(action: SetLoginInfoAction): LoginInfo? {
        return action.value.loginInfo
    }
}

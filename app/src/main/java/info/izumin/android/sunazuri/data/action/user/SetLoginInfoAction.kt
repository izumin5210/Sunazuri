package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.domain.entity.LoginInfo

/**
 * Created by izumin on 5/25/2016 AD.
 */
class SetLoginInfoAction : BaseAction<SetLoginInfoAction.RequestValue>() {
    data class RequestValue(
            val loginInfo: LoginInfo?
    ) : BaseAction.RequestValue;
}

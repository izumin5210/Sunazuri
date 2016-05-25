package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser

/**
 * Created by izumin on 5/25/2016 AD.
 */
class SetCurrentUserAction : BaseAction<SetCurrentUserAction.RequestValue>() {
    data class RequestValue(
            val user: AuthorizedUser?
    ) : BaseAction.RequestValue;
}

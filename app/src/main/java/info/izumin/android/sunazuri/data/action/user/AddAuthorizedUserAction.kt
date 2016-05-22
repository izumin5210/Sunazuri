package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser

/**
 * Created by izumin on 5/22/2016 AD.
 */
class AddAuthorizedUserAction : BaseAction<AddAuthorizedUserAction.RequestValue>() {
    data class RequestValue(
            val user: AuthorizedUser
    ) : BaseAction.RequestValue;
}

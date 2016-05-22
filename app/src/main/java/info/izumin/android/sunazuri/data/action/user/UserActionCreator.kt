package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.droidux.Action
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import javax.inject.Inject

/**
 * Created by izumin on 5/22/2016 AD.
 */
class UserActionCreator @Inject constructor(
        val oauthRepo: OauthRepository
) {
    fun createAddAuthorizedUserAction(user: AuthorizedUser): Action {
        val action = AddAuthorizedUserAction()
        action.value = AddAuthorizedUserAction.RequestValue(user)
        return action
    }

    fun createAuthAction(callbackUri: String): Action {
        val action = OauthAction(oauthRepo, this)
        action.value = OauthAction.RequestValue(callbackUri)
        return action
    }
}

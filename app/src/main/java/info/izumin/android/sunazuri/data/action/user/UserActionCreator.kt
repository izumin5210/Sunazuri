package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.droidux.Action
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import info.izumin.android.sunazuri.domain.repository.UsersRepository
import javax.inject.Inject

/**
 * Created by izumin on 5/22/2016 AD.
 */
class UserActionCreator @Inject constructor(
        val oauthRepo: OauthRepository,
        val usersRepo: UsersRepository
) {
    fun createAddAuthorizedUserAction(user: AuthorizedUser): Action {
        val action = AddAuthorizedUserAction()
        action.value = AddAuthorizedUserAction.RequestValue(user)
        return action
    }

    fun createLoadCurrentUserAction(): Action {
        return LoadCurrentUserAction(usersRepo, this);
    }

    fun createAuthAction(callbackUri: String): Action {
        val action = OauthAction(oauthRepo, this)
        action.value = OauthAction.RequestValue(callbackUri)
        return action
    }

    fun createSetCurrentUserAction(user: AuthorizedUser?): Action {
        val action = SetCurrentUserAction()
        action.value = SetCurrentUserAction.RequestValue(user)
        return action
    }
}

package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.droidux.Action
import info.izumin.android.sunazuri.data.action.team.TeamActionCreator
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.entity.LoginInfo
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import info.izumin.android.sunazuri.domain.repository.UsersRepository
import javax.inject.Inject

/**
 * Created by izumin on 5/22/2016 AD.
 */
class UserActionCreator @Inject constructor(
        val oauthRepo: OauthRepository,
        val usersRepo: UsersRepository,
        val teamActionCreator: TeamActionCreator
) {
    fun createAddAuthorizedUserAction(user: AuthorizedUser): Action {
        val action = AddAuthorizedUserAction()
        action.value = AddAuthorizedUserAction.RequestValue(user)
        return action
    }

    fun createLoadLoginInfoAction(): Action {
        return LoadLoginInfoAction(usersRepo, this);
    }

    fun createAuthAction(callbackUri: String): Action {
        val action = OauthAction(oauthRepo, this, teamActionCreator)
        action.value = OauthAction.RequestValue(callbackUri)
        return action
    }

    fun createSetLoginInfo(loginInfo: LoginInfo?): Action {
        val action = SetLoginInfoAction()
        action.value = SetLoginInfoAction.RequestValue(loginInfo)
        return action
    }
}

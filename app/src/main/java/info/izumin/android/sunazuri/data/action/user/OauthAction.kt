package info.izumin.android.sunazuri.data.action.user;

import android.net.Uri
import info.izumin.android.droidux.Action
import info.izumin.android.droidux.Dispatcher
import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.data.action.common.BaseAsyncAction
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import rx.Observable

/**
 * Created by izumin on 5/22/2016 AD.
 */
class OauthAction constructor(
        val repo: OauthRepository,
        val userActionCreator: UserActionCreator
) : BaseAsyncAction<OauthAction.RequestValue>() {

    override fun call(dispatcher: Dispatcher): Observable<Action> {
        val code = Uri.parse(value.callbackUri).getQueryParameter("code")
        return repo.getToken(code)
                .toObservable()
                .map { userActionCreator.createAddAuthorizedUserAction(it) }
    }

    data class RequestValue(
            val callbackUri: String
    ) : BaseAction.RequestValue
}

package info.izumin.android.sunazuri.data.action.user;

import android.net.Uri
import info.izumin.android.droidux.Action
import info.izumin.android.droidux.Dispatcher
import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.data.action.common.BaseAsyncAction
import info.izumin.android.sunazuri.data.action.team.TeamActionCreator
import info.izumin.android.sunazuri.domain.exception.WebApiError
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import rx.Observable

/**
 * Created by izumin on 5/22/2016 AD.
 */
class OauthAction constructor(
        val repo: OauthRepository,
        val userActionCreator: UserActionCreator,
        val teamActionCreator: TeamActionCreator
) : BaseAsyncAction<OauthAction.RequestValue>() {

    override fun call(dispatcher: Dispatcher): Observable<Action> {
        val uri = Uri.parse(value.callbackUri)
        val code = uri.getQueryParameter("code")
        if (code.isEmpty()) {
            val error = uri.getQueryParameter("error")
            val msg = uri.getQueryParameter("error_description")
            return Observable.error(WebApiError(error, msg))
        }
        return repo.getToken(code)
                .toObservable()
                .flatMap {
                    dispatcher.dispatch(userActionCreator.createAddAuthorizedUserAction(it))
                            .map({ _a -> it })
                }
                .map { teamActionCreator.createFetchTeamsAction(it) }
                .flatMap { dispatcher.dispatch(it) }
                .map { userActionCreator.createLoadLoginInfoAction() }
    }

    data class RequestValue(
            val callbackUri: String
    ) : BaseAction.RequestValue
}

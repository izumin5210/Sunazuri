package info.izumin.android.sunazuri.data.action.user;

import info.izumin.android.droidux.Action
import info.izumin.android.droidux.Dispatcher
import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.data.action.common.BaseAsyncAction
import info.izumin.android.sunazuri.domain.repository.UsersRepository
import rx.Observable

/**
 * Created by izumin on 5/25/2016 AD.
 */
class LoadLoginInfoAction constructor(
        val repo: UsersRepository,
        val userActionCreator: UserActionCreator
) : BaseAsyncAction<LoadLoginInfoAction.RequestValue>() {

    override fun call(dispatcher: Dispatcher): Observable<Action> {
        return repo.loginInfo.defaultIfEmpty(null)
                .map { userActionCreator.createSetLoginInfo(it) }
    }

    class RequestValue : BaseAction.RequestValue
}

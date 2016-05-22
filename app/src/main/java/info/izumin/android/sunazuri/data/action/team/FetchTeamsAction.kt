package info.izumin.android.sunazuri.data.action.team;

import info.izumin.android.droidux.Action
import info.izumin.android.droidux.Dispatcher
import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.data.action.common.BaseAsyncAction
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.repository.TeamsRepository
import rx.Observable

/**
 * Created by izumin on 5/22/2016 AD.
 */
class FetchTeamsAction(
        val teamsRepo: TeamsRepository,
        val teamActionCreator: TeamActionCreator
) : BaseAsyncAction<FetchTeamsAction.RequestValue>() {

    override fun call(dispatcher: Dispatcher): Observable<Action> {
        return teamsRepo.get(value.user)
                .toObservable()
                .map { teamActionCreator.createAddTeamsAction(it) }
    }

    data class RequestValue(
            val user: AuthorizedUser
    ) : BaseAction.RequestValue
}

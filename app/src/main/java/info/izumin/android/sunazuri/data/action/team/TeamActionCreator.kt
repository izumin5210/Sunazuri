package info.izumin.android.sunazuri.data.action.team

import info.izumin.android.droidux.Action
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.entity.Team
import info.izumin.android.sunazuri.domain.repository.TeamsRepository
import javax.inject.Inject

/**
 * Created by izumin on 5/22/2016 AD.
 */
class TeamActionCreator @Inject constructor(
        val teamsRepo: TeamsRepository
) {
    fun createAddTeamsAction(teams: Collection<Team>) : Action {
        val action = AddTeamsAction()
        action.value = AddTeamsAction.RequsetValue(teams)
        return action
    }

    fun createFetchTeamsAction(user: AuthorizedUser) : Action {
        val action = FetchTeamsAction(teamsRepo, this)
        action.value = FetchTeamsAction.RequestValue(user)
        return action
    }
}

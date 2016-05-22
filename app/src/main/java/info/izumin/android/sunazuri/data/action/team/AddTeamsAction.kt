package info.izumin.android.sunazuri.data.action.team;

import info.izumin.android.sunazuri.data.action.common.BaseAction
import info.izumin.android.sunazuri.domain.entity.Team

/**
 * Created by izumin on 5/22/2016 AD.
 */
class AddTeamsAction : BaseAction<AddTeamsAction.RequsetValue>() {
    data class RequsetValue(
            val teams: Collection<Team>
    ) : BaseAction.RequestValue
}

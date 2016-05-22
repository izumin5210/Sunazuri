package info.izumin.android.sunazuri.data.reducer;

import info.izumin.android.droidux.annotation.Dispatchable
import info.izumin.android.droidux.annotation.Reducer
import info.izumin.android.sunazuri.data.action.team.AddTeamsAction
import info.izumin.android.sunazuri.domain.model.Teams
import java.util.*

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Reducer(Teams::class)
class TeamsReducer {
    @Dispatchable(AddTeamsAction::class)
    fun add(state: Teams, action: AddTeamsAction): Teams {
        return Teams(
                ArrayList(state).plus(ArrayList(action.value.teams))
                        .reversed().distinctBy { it.name }.reversed()
        )
    }
}

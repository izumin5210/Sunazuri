package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import retrofit2.mock.BehaviorDelegate
import rx.Single
import java.util.*

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockTeamsApi(val delegate: BehaviorDelegate<TeamsApi>) : TeamsApi {

    val TEAM = TeamEntity()
    val TEAM_STATS = TeamStatsEntity()

    val TEAM_LIST = ArrayList<TeamEntity>()

    init {
        TEAM.name           = "example_team"
        TEAM.description    = "example team description"
        TEAM.icon           = "http://example.com/example_team.png"
        TEAM.privacy        = "open"

        TEAM_STATS.members  = 20
        TEAM_STATS.posts    = 1959
        TEAM_STATS.comments = 2695
        TEAM_STATS.dailyActiveUsers     = 8
        TEAM_STATS.weeklyActiveUsers    = 14
        TEAM_STATS.monthlyActiveUsers   = 13

        TEAM_LIST.add(TEAM)
    }

    override fun fetch(accessToken: String?): Single<MutableList<TeamEntity>>? {
        return delegate.returningResponse(TEAM_LIST).fetch(accessToken)
    }

    override fun fetch(teamName: String?, accessToken: String?): Single<TeamEntity>? {
        return delegate.returningResponse(TEAM).fetch(teamName, accessToken)
    }

    override fun stats(teamName: String?, accessToken: String?): Single<TeamStatsEntity>? {
        return delegate.returningResponse(TEAM_STATS).stats(teamName, accessToken)
    }
}
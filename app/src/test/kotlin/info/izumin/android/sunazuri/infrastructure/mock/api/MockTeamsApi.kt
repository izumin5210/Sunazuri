package info.izumin.android.sunazuri.infrastructure.mock.api

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.MockRetrofit
import rx.Observable
import rx.Single

/**
 * Created by izumin on 5/21/2016 AD.
 */
class MockTeamsApi(val delegate: BehaviorDelegate<TeamsApi>) : TeamsApi {
    companion object {
        fun getInstance(mockRetrofit: MockRetrofit): MockTeamsApi {
            return MockTeamsApi(mockRetrofit.create(TeamsApi::class.java))
        }
    }

    var team = DataFactory.createTeamEntity()
        get set
    var stats = DataFactory.createTeamStatsEntity()
        get set

    init {
        stats.team = team
    }

    override fun fetch(accessToken: String?): Single<MutableList<TeamEntity>>? {
        return delegate.returningResponse(arrayListOf(team)).fetch(accessToken)
    }

    override fun fetch(teamName: String?, accessToken: String?): Single<TeamEntity>? {
        return delegate.returningResponse(team).fetch(teamName, accessToken)
    }

    override fun stats(teamName: String?, accessToken: String?): Single<TeamStatsEntity>? {
        return delegate.returningResponse(stats).stats(teamName, accessToken)
    }

    override fun categories(teamName: String?, accessToken: String?): Observable<TeamsApi.Category>? {
        // TODO: Not yet implemented.
        throw UnsupportedOperationException()
    }
}
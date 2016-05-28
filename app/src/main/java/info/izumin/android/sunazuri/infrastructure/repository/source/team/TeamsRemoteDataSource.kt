package info.izumin.android.sunazuri.infrastructure.repository.source.team;

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import rx.Observable
import rx.Single

/**
 * Created by izumin on 5/13/2016 AD.
 */
internal class TeamsRemoteDataSource (
        val teamsApi: TeamsApi,
        val teamsDao: TeamsDao
) : TeamsDataSource {
    override fun getCurrentTeam(): Observable<TeamStatsEntity>? {
        throw UnsupportedOperationException()
    }

    override fun getTeams(token: AccessTokenEntity): Single<MutableList<TeamStatsEntity>>? {
        return teamsApi.fetch(token.accessToken)
                .toObservable()
                .flatMap { Observable.from(it) }
                .flatMap { team ->
                    teamsApi.stats(team.name, token.accessToken)
                            .toObservable()
                            .doOnNext { stats ->
                                stats.team = team
                                team.user = token.user
                            }
                }
                .toList()
                .doOnNext { teamsDao.updateStatsAll(it) }
                .toSingle()
    }
}

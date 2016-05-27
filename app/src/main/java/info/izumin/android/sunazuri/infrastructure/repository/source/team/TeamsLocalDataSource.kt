package info.izumin.android.sunazuri.infrastructure.repository.source.team

import info.izumin.android.sunazuri.infrastructure.cache.LoginCache
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import rx.Observable
import rx.Single

/**
 * Created by izumin on 5/22/2016 AD.
 */
internal class TeamsLocalDataSource(
        val teamsDao: TeamsDao,
        val loginCache: LoginCache
) : TeamsDataSource {
    override fun getCurrentTeam(): Observable<TeamStatsEntity> {
        if (loginCache.isTeamCached) {
            return teamsDao.findByName(loginCache.teamId)
        } else {
            return Observable.empty()
        }
    }

    override fun getTeams(token: AccessTokenEntity): Single<MutableList<TeamStatsEntity>> {
        return teamsDao.findAll(token.user)
    }
}

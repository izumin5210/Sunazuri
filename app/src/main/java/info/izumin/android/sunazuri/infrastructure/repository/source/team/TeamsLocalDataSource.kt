package info.izumin.android.sunazuri.infrastructure.repository.source.team

import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import rx.Single

/**
 * Created by izumin on 5/22/2016 AD.
 */
internal class TeamsLocalDataSource(
        val teamsDao: TeamsDao
) : TeamsDataSource {
    override fun getTeams(token: AccessTokenEntity): Single<MutableList<TeamStatsEntity>> {
        return teamsDao.findAll(token.user)
    }
}

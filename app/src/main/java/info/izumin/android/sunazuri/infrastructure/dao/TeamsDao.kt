package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Singleton
class TeamsDao @Inject constructor(val orma: OrmaProvider) {
    fun findAll(user: AuthorizedUserEntity) : Single<MutableList<TeamStatsEntity>> {
        return  teamRelation().userEq(user).selector()
                .executeAsObservable()
                .flatMap { team ->
                    teamStatsRelation().teamEq(team).selector().executeAsObservable()
                }
                .toList()
                .toSingle()
    }

    fun updateTeamAll(teamList: Collection<TeamEntity>) {
        orma.db.transactionSync {
            for (team in teamList) {
                insertTeam(team)
            }
        }
    }

    fun updateStatsAll(teamStatsList: Collection<TeamStatsEntity>) {
        orma.db.transactionSync {
            for (stats in teamStatsList) {
                insertTeamStats(stats)
            }
        }
    }

    private fun insertTeam(team: TeamEntity) {
        teamRelation().userEq(team.user).upserter().execute(team)
    }

    private fun insertTeamStats(stats: TeamStatsEntity) {
        insertTeam(stats.team)
        teamStatsRelation().teamEq(stats.team).upserter().execute(stats)
    }

    private fun teamRelation() = orma.db.relationOfTeamEntity()
    private fun teamStatsRelation() = orma.db.relationOfTeamStatsEntity()
}
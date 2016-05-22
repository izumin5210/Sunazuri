package info.izumin.android.sunazuri.infrastructure.entity.mapper

import info.izumin.android.sunazuri.domain.entity.Team
import info.izumin.android.sunazuri.domain.entity.TeamStats
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by izumin on 5/22/2016 AD.
 */
@Singleton
class TeamMapper @Inject constructor() {
    fun transform(entity: TeamEntity?): Team? {
        var team: Team? = null
        if (entity != null) {
            team = Team(
                    entity.name,
                    entity.privacy,
                    entity.description,
                    entity.icon,
                    entity.user.id
            )
        }
        return team
    }

    fun transform(entity: TeamStatsEntity?): Team? {
        var team: Team? = null
        if (entity != null && entity.team != null) {
            val stats = TeamStats(
                    entity.members,
                    entity.posts,
                    entity.comments,
                    entity.stars,
                    entity.dailyActiveUsers,
                    entity.weeklyActiveUsers,
                    entity.monthlyActiveUsers
            )
            team = Team(
                    entity.team.name,
                    entity.team.privacy,
                    entity.team.description,
                    entity.team.icon,
                    entity.team.user.id,
                    stats
            )
        }
        return team
    }

    fun transformFromTeamEntities(entities: List<TeamEntity>?): MutableList<Team?> {
        return Observable.from(entities).map { transform(it) }
                .toList().toBlocking().first()
    }

    fun transformFromTeamStatsEntities(entities: List<TeamStatsEntity>?): MutableList<Team?> {
        return Observable.from(entities).map { transform(it) }
                .toList().toBlocking().first()
    }
}
package info.izumin.android.sunazuri.infrastructure.mock

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import java.util.*

/**
 * Created by izumin on 5/28/2016 AD.
 */

object DataFactory {
    val DEFAULT_USER_ID: Long = 1
    val DEFAULT_TEAM_NAME = "example_team1"

    fun createAuthorizedUserEntity(
            userId: Long = DEFAULT_USER_ID
    ): AuthorizedUserEntity {
        val user = AuthorizedUserEntity()
        user.id         = userId
        user.name       = "test user"
        user.screenName = "test_user"
        user.createdAt  = Date()
        user.updatedAt  = Date()
        user.email      = "test@example.com"
        user.icon       = "http://example.com/test_user.png"
        return user
    }

    fun createAccessTokenEntity(
            userId: Long = DEFAULT_USER_ID
    ): AccessTokenEntity {
        val token = AccessTokenEntity()
        token.accessToken   = "testtoken"
        token.tokenType     = "bearer"
        token.scope         = "read+write"
        token.user = createAuthorizedUserEntity(userId)
        return token
    }

    fun createTeamEntity(
            teamName: String = DEFAULT_TEAM_NAME
    ): TeamEntity {
        val team = TeamEntity()
        team.name           = teamName
        team.description    = "example team description"
        team.icon           = "http://example.com/example_team.png"
        team.privacy        = "open"
        return team
    }

    fun createTeamStatsEntity(
            teamName: String = DEFAULT_TEAM_NAME
    ): TeamStatsEntity {
        val stats = TeamStatsEntity()
        stats.members  = 20
        stats.posts    = 1959
        stats.comments = 2695
        stats.dailyActiveUsers     = 8
        stats.weeklyActiveUsers    = 14
        stats.monthlyActiveUsers   = 13
        stats.team = createTeamEntity(teamName)
        return stats
    }
}

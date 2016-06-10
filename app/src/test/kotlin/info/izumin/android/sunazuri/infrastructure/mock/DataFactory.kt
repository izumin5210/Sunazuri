package info.izumin.android.sunazuri.infrastructure.mock

import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
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
        stats.members   = 20
        stats.posts     = 1959
        stats.comments  = 2695
        stats.stars     = 3245
        stats.dailyActiveUsers     = 8
        stats.weeklyActiveUsers    = 14
        stats.monthlyActiveUsers   = 13
        stats.team = createTeamEntity(teamName)
        return stats
    }

    fun createCategoryApiResponse(): List<TeamsApi.Category> {
        val category0000 = TeamsApi.Category()
        category0000.name = "DailyReport"
        category0000.count = 12
        val category0100 = TeamsApi.Category()
        category0100.name = "2016"
        category0100.count = 5
        val category0110 = TeamsApi.Category()
        category0110.name = "02"
        category0110.count = 5
        val category0111 = TeamsApi.Category()
        category0111.name = "04"
        category0111.count = 2
        val category0112 = TeamsApi.Category()
        category0112.name = "20"
        category0112.count = 3
        val category1000 = TeamsApi.Category()
        category1000.name = "Note"
        category1000.count = 13

        category0110.children = listOf(category0111, category0112)
        category0100.children = listOf(category0110)
        category0000.children = listOf(category0100)

        return listOf(category0000, category1000)
    }
}

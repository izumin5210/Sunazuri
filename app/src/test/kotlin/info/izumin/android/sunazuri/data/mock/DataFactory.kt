package info.izumin.android.sunazuri.data.mock

import info.izumin.android.sunazuri.domain.entity.AccessToken
import info.izumin.android.sunazuri.domain.entity.AuthorizedUser
import info.izumin.android.sunazuri.domain.entity.Team
import info.izumin.android.sunazuri.domain.entity.TeamStats
import java.util.*

/**
 * Created by izumin on 5/28/2016 AD.
 */

object DataFactory {
    val DEFAULT_USER_ID: Long = 1
    val DEFAULT_TEAM_NAME = "example_team1"

    fun createAuthorizedUser(
            userId: Long = DEFAULT_USER_ID
    ): AuthorizedUser {
        return AuthorizedUser(
                userId,
                "test user",
                "test_user",
                Date(),
                Date(),
                "test@example.com",
                "http://example.com/test_user.png",
                createAccessToken()
        )
    }

    fun createAccessToken(): AccessToken {
        return AccessToken(
                "testtoken",
                "bearer",
                "read+write"
        )
    }

    fun createTeam(
            teamName: String = DEFAULT_TEAM_NAME,
            userId: Long = DEFAULT_USER_ID
    ): Team {
        return Team(
                teamName,
                "example team description",
                "http://example.com/example_team.png",
                "open",
                userId,
                createTeamStats()
        )
    }

    fun createTeamStats(): TeamStats {
        return TeamStats(
                20,
                1959,
                2695,
                3245,
                8,
                14,
                13
        )
    }
}

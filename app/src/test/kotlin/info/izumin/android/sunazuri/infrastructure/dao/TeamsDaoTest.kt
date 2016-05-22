package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import rx.observers.TestSubscriber
import java.util.*
import kotlin.test.expect

/**
 * Created by izumin on 5/22/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class TeamsDaoTest {

    companion object {
        val USER_ID: Long = 1
        val USER = AuthorizedUserEntity()
        val TEAM = TeamEntity()
        val TEAM_STATS = TeamStatsEntity()

        val TEAM_LIST = ArrayList<TeamEntity>()
        val TEAM_STATS_LIST = ArrayList<TeamStatsEntity>()

        init {
            USER.id         = USER_ID
            USER.name       = "test user"
            USER.screenName = "test_user"
            USER.createdAt  = Date()
            USER.updatedAt  = Date()
            USER.icon       = "http://example.com/test_user.png"
            USER.email      = "test@example.com"

            TEAM.name           = "example_team"
            TEAM.description    = "example team description"
            TEAM.icon           = "http://example.com/example_team.png"
            TEAM.privacy        = "open"
            TEAM.user           = USER

            TEAM_STATS.members  = 20
            TEAM_STATS.posts    = 1959
            TEAM_STATS.comments = 2695
            TEAM_STATS.dailyActiveUsers     = 8
            TEAM_STATS.weeklyActiveUsers    = 14
            TEAM_STATS.monthlyActiveUsers   = 13
            TEAM_STATS.team = TEAM

            TEAM_LIST.add(TEAM)
            TEAM_STATS_LIST.add(TEAM_STATS)
        }
    }

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var orma: OrmaProvider
    lateinit var dao: TeamsDao

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        dao = TeamsDao(orma)

        orma.db.relationOfAuthorizedUserEntity().inserter().execute(USER)
    }

    @Test
    fun testFindAll() {
        dao.updateStatsAll(TEAM_STATS_LIST)
        val subscriber = TestSubscriber<List<TeamStatsEntity>>()

        dao.findAll(USER).subscribe(subscriber)
        subscriber.awaitTerminalEvent()

        val statsList = subscriber.onNextEvents[0]
        expect(1, { statsList.size })
        expect(USER_ID, { statsList[0].team.user.id })
    }

    @Test
    fun testInsertTeam() {
        dao.updateTeamAll(TEAM_LIST)

        expect(1, { orma.db.selectFromTeamEntity().userEq(USER_ID).count() })
    }

    @Test
    fun testInsertTeamWithUpdate() {
        dao.updateTeamAll(TEAM_LIST)
        TEAM.description = "new team description"
        dao.updateTeamAll(TEAM_LIST)

        expect("new team description", { orma.db.selectFromTeamEntity().userEq(USER_ID).value().description })
    }

    @Test
    fun testInsertStats() {
        dao.updateStatsAll(TEAM_STATS_LIST)

        expect(1, { orma.db.selectFromTeamEntity().userEq(USER_ID).count() })
        expect(1, { orma.db.selectFromTeamStatsEntity().teamEq(TEAM.name).count() })
    }

    @Test
    fun testInsertStatsWithUpdate() {
        dao.updateStatsAll(TEAM_STATS_LIST)
        TEAM.description = "new team description"
        TEAM_STATS.dailyActiveUsers += 32
        dao.updateStatsAll(TEAM_STATS_LIST)

        expect("new team description", { orma.db.selectFromTeamEntity().userEq(USER_ID).value().description })
        expect(40, { orma.db.selectFromTeamStatsEntity().teamEq(TEAM.name).value().dailyActiveUsers })
    }
}
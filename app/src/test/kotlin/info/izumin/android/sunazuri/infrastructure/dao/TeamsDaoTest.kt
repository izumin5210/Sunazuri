package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import rx.observers.TestSubscriber
import kotlin.test.expect

/**
 * Created by izumin on 5/22/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
class TeamsDaoTest {

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var orma: OrmaProvider
    lateinit var dao: TeamsDao

    lateinit var token: AccessTokenEntity
    lateinit var stats: TeamStatsEntity

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        dao = TeamsDao(orma)

        token = DataFactory.createAccessTokenEntity()
        stats = DataFactory.createTeamStatsEntity()
        stats.team.user = token.user

        orma.db.relationOfAuthorizedUserEntity().inserter().execute(token.user)
    }

    @Test
    fun testFindAll() {
        dao.updateStatsAll(arrayListOf(stats))
        val subscriber = TestSubscriber<List<TeamStatsEntity>>()

        dao.findAll(token.user).subscribe(subscriber)
        subscriber.awaitTerminalEvent()

        val statsList = subscriber.onNextEvents[0]
        expect(1, { statsList.size })
        expect(token.user.id, { statsList[0].team.user.id })
    }

    @Test
    fun testInsertTeam() {
        dao.updateTeamAll(arrayListOf(stats.team))

        expect(1, { orma.db.selectFromTeamEntity().userEq(token.user.id).count() })
    }

    @Test
    fun testInsertTeamWithUpdate() {
        dao.updateTeamAll(arrayListOf(stats.team))
        stats.team.description = "new team description"
        dao.updateTeamAll(arrayListOf(stats.team))

        expect("new team description", { orma.db.selectFromTeamEntity().userEq(token.user).value().description })
    }

    @Test
    fun testInsertStats() {
        dao.updateStatsAll(arrayListOf(stats))

        expect(1, { orma.db.selectFromTeamEntity().userEq(token.user).count() })
        expect(1, { orma.db.selectFromTeamStatsEntity().teamEq(stats.team.name).count() })
    }

    @Test
    fun testInsertStatsWithUpdate() {
        dao.updateStatsAll(arrayListOf(stats))
        stats.team.description = "new team description"
        stats.dailyActiveUsers += 32
        dao.updateStatsAll(arrayListOf(stats))

        expect("new team description", { orma.db.selectFromTeamEntity().userEq(token.user).value().description })
        expect(40, { orma.db.selectFromTeamStatsEntity().teamEq(stats.team).value().dailyActiveUsers })
    }
}
package info.izumin.android.sunazuri.infrastructure.repository.source.team

import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.api.TeamsApi
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import info.izumin.android.sunazuri.infrastructure.mock.api.MockTeamsApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.mock.MockRetrofit
import rx.observers.TestSubscriber
import java.util.*
import kotlin.test.expect

/**
 * Created by izumin on 5/22/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class TeamRemoteDataSourceTest {

    companion object {
        val USER = AuthorizedUserEntity()
        val USER_ID: Long = 1
        val TOKEN = AccessTokenEntity()

        init {
            USER.id         = USER_ID
            USER.name       = "test user"
            USER.screenName = "test_user"
            USER.createdAt  = Date()
            USER.updatedAt  = Date()
            USER.email      = "test@example.com"
            USER.icon       = "http://example.com/test_user.png"

            TOKEN.accessToken   = "testtoken"
            TOKEN.tokenType     = "bearer"
            TOKEN.scope         = "read+write"
            TOKEN.user          = USER
        }
    }

    val context = RuntimeEnvironment.application.applicationContext

    val retrofit = Retrofit.Builder()
            .baseUrl("http://api.example.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    val mockRetrofit = MockRetrofit.Builder(retrofit).build()

    val teamsApi = MockTeamsApi(mockRetrofit.create(TeamsApi::class.java))

    lateinit var orma: OrmaProvider
    lateinit var teamsDao: TeamsDao

    lateinit var dataSource: TeamsDataSource

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        teamsDao = TeamsDao(orma)

        dataSource = TeamsRemoteDataSource(teamsApi, teamsDao)

        orma.db.insertIntoAuthorizedUserEntity(USER)
        orma.db.insertIntoAccessTokenEntity(TOKEN)
    }

    @Test
    fun testGetTeams() {
        val subscriber = TestSubscriber<List<TeamStatsEntity>>()
        dataSource.getTeams(TOKEN).subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        val statsList = subscriber.onNextEvents[0]

        expect(teamsApi.TEAM_STATS.members, { statsList[0].members })
        expect(teamsApi.TEAM.name, { statsList[0].team.name })
        expect(USER.id, { statsList[0].team.user.id })
        expect(teamsApi.TEAM_STATS.comments, {
            orma.db.selectFromTeamStatsEntity().teamEq(teamsApi.TEAM).value().comments
        })
        expect(teamsApi.TEAM.description, {
            orma.db.selectFromTeamEntity().nameEq(teamsApi.TEAM.name).value().description
        })
        expect(teamsApi.TEAM.name, {
            orma.db.selectFromTeamEntity().userEq(USER).value().name
        })
    }
}

package info.izumin.android.sunazuri.infrastructure.repository.source.team

import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider
import info.izumin.android.sunazuri.infrastructure.dao.TeamsDao
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import info.izumin.android.sunazuri.infrastructure.mock.api.MockTeamsApi
import info.izumin.android.sunazuri.infrastructure.mock.cache.FakeLoginCache
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.mock.MockRetrofit
import rx.observers.TestSubscriber
import kotlin.test.expect

/**
 * Created by izumin on 5/22/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
class TeamRemoteDataSourceTest {

    val context = RuntimeEnvironment.application.applicationContext

    val retrofit = Retrofit.Builder()
            .baseUrl("http://api.example.com")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()
    val mockRetrofit = MockRetrofit.Builder(retrofit).build()

    val teamsApi = MockTeamsApi.getInstance(mockRetrofit)

    val loginCache = FakeLoginCache()

    lateinit var orma: OrmaProvider
    lateinit var teamsDao: TeamsDao

    lateinit var dataSource: TeamsDataSource

    lateinit var user: AuthorizedUserEntity
    lateinit var token: AccessTokenEntity

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        teamsDao = TeamsDao(orma)

        dataSource = TeamsRemoteDataSource(teamsApi, teamsDao, loginCache)

        user = DataFactory.createAuthorizedUserEntity()
        token = DataFactory.createAccessTokenEntity()

        orma.db.insertIntoAuthorizedUserEntity(user)
        orma.db.insertIntoAccessTokenEntity(token)
    }

    @After
    fun tearDown() {
        loginCache.removeAll()
    }

    @Test
    fun testGetTeams() {
        val subscriber = TestSubscriber<List<TeamStatsEntity>>()
        dataSource.getTeams(token).subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        val statsList = subscriber.onNextEvents[0]

        expect(teamsApi.stats.members, {
            statsList[0].members
        })
        expect(teamsApi.team.name, {
            statsList[0].team.name
        })
        expect(user.id, {
            statsList[0].team.user.id
        })
        expect(teamsApi.stats.comments, {
            orma.db.selectFromTeamStatsEntity().teamEq(teamsApi.team).value().comments
        })
        expect(teamsApi.team.description, {
            orma.db.selectFromTeamEntity().nameEq(teamsApi.team.name).value().description
        })
        expect(teamsApi.team.name, {
            orma.db.selectFromTeamEntity().userEq(user).value().name
        })
        expect(teamsApi.team.name, {
            loginCache.teamId
        })
    }
}

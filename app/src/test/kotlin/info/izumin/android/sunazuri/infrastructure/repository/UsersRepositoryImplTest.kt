package info.izumin.android.sunazuri.infrastructure.repository

import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.domain.entity.LoginInfo
import info.izumin.android.sunazuri.domain.repository.UsersRepository
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamStatsEntity
import info.izumin.android.sunazuri.infrastructure.entity.mapper.AuthorizedUserMapper
import info.izumin.android.sunazuri.infrastructure.entity.mapper.TeamMapper
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthDataSource
import info.izumin.android.sunazuri.infrastructure.repository.source.oauth.OauthDataSourceFactory
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsDataSource
import info.izumin.android.sunazuri.infrastructure.repository.source.team.TeamsDataSourceFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import rx.Observable
import rx.observers.TestSubscriber
import kotlin.test.expect

/**
 * Created by izumin on 5/26/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class UsersRepositoryImplTest {

    lateinit var oauthLocalDataSource: OauthDataSource
    lateinit var teamsLocalDataSource: TeamsDataSource
    lateinit var oauthDataSourceFactory: OauthDataSourceFactory
    lateinit var teamsDataSourceFactory: TeamsDataSourceFactory

    lateinit var repo: UsersRepository

    lateinit var token: AccessTokenEntity
    lateinit var stats: TeamStatsEntity

    @Before
    fun setUp() {
        token = DataFactory.createAccessTokenEntity()
        stats = DataFactory.createTeamStatsEntity()
        stats.team.user = token.user

        oauthLocalDataSource = Mockito.mock(OauthDataSource::class.java)
        teamsLocalDataSource = Mockito.mock(TeamsDataSource::class.java)
        oauthDataSourceFactory = Mockito.mock(OauthDataSourceFactory::class.java)
        teamsDataSourceFactory = Mockito.mock(TeamsDataSourceFactory::class.java)

        Mockito.`when`(oauthDataSourceFactory.createLocalDataSource()).thenReturn(oauthLocalDataSource)
        Mockito.`when`(teamsDataSourceFactory.createLocalDataSource()).thenReturn(teamsLocalDataSource)

        val teamMapper = TeamMapper()
        val authorizedUserMapper = AuthorizedUserMapper(teamMapper)

        repo = UsersRepositoryImpl(oauthDataSourceFactory, teamsDataSourceFactory, authorizedUserMapper, teamMapper)
    }

    @Test
    fun testGetLoginInfo_whenCurrentUserIsEmpty() {
        Mockito.`when`(oauthLocalDataSource.currentToken).thenReturn(Observable.empty())
        Mockito.`when`(teamsLocalDataSource.currentTeam).thenReturn(Observable.just(stats))

        val subscriber = TestSubscriber<LoginInfo>()

        repo.loginInfo.subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertCompleted()
        subscriber.assertNoErrors()
        subscriber.assertNoValues()
    }

    @Test
    fun testGetLoginInfo_whenCurrentTeamIsEmpty() {
        Mockito.`when`(oauthLocalDataSource.currentToken).thenReturn(Observable.just(token))
        Mockito.`when`(teamsLocalDataSource.currentTeam).thenReturn(Observable.empty())

        val subscriber = TestSubscriber<LoginInfo>()

        repo.loginInfo.subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertCompleted()
        subscriber.assertNoErrors()
        subscriber.assertNoValues()
    }

    @Test
    fun testGetLoginInfo_whenReturnsOneLoginInfo() {
        Mockito.`when`(oauthLocalDataSource.currentToken).thenReturn(Observable.just(token))
        Mockito.`when`(teamsLocalDataSource.currentTeam).thenReturn(Observable.just(stats))

        val subscriber = TestSubscriber<LoginInfo>()

        repo.loginInfo.subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertCompleted()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)

        val info = subscriber.onNextEvents[0]

        expect(token.user.id, { info.user.id })
        expect(stats.team.name, { info.team.name })
    }
}

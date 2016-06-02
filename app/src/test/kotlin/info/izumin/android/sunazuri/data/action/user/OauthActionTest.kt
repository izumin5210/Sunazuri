package info.izumin.android.sunazuri.data.action.user

import info.izumin.android.droidux.Action
import info.izumin.android.droidux.thunk.ThunkMiddleware
import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.data.action.team.TeamActionCreator
import info.izumin.android.sunazuri.data.mock.DataFactory
import info.izumin.android.sunazuri.data.reducer.AuthorizedUsersReducer
import info.izumin.android.sunazuri.data.reducer.LoginInfoReducer
import info.izumin.android.sunazuri.data.reducer.TeamsReducer
import info.izumin.android.sunazuri.domain.DroiduxRootStore
import info.izumin.android.sunazuri.domain.RootStore
import info.izumin.android.sunazuri.domain.model.AuthorizedUsers
import info.izumin.android.sunazuri.domain.model.Teams
import info.izumin.android.sunazuri.domain.repository.OauthRepository
import info.izumin.android.sunazuri.domain.repository.TeamsRepository
import info.izumin.android.sunazuri.domain.repository.UsersRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config
import rx.Single
import rx.observers.TestSubscriber
import kotlin.test.expect

/**
 * Created by izumin on 5/29/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class OauthActionTest {

    lateinit var oauthRepo: OauthRepository
    lateinit var usersRepo: UsersRepository
    lateinit var teamsRepo: TeamsRepository
    lateinit var userActionCreator: UserActionCreator
    lateinit var teamActionCreator: TeamActionCreator

    val USER_ID = 1L
    val USER = DataFactory.createAuthorizedUser(USER_ID)
    val TEAM_NAME = "test_team"
    val TEAM = DataFactory.createTeam(TEAM_NAME)

    lateinit var store: RootStore

    @Before
    fun setUp() {
        oauthRepo = Mockito.mock(OauthRepository::class.java)
        usersRepo = Mockito.mock(UsersRepository::class.java)
        teamsRepo = Mockito.mock(TeamsRepository::class.java)

        teamActionCreator = TeamActionCreator(teamsRepo)
        userActionCreator = UserActionCreator(oauthRepo, usersRepo, teamActionCreator)

        store = DroiduxRootStore.builder()
                .setReducer(AuthorizedUsersReducer(), AuthorizedUsers())
                .setReducer(TeamsReducer(), Teams())
                .setReducer(LoginInfoReducer())
                .addMiddleware(ThunkMiddleware())
                .build()
    }

    @Test
    fun testName() {
        Mockito.stub(oauthRepo.getToken(Mockito.anyString())).toReturn(Single.just(USER))
        Mockito.stub(teamsRepo.get(Mockito.any())).toReturn(Single.just(listOf(TEAM)))

        val callbackUri = "sunazuri://esa.example.com?code=52652c14c863181c40b231283b7ccac6"
        val subscriber = TestSubscriber<Action>()
        store.dispatch(userActionCreator.createAuthAction(callbackUri)).subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        subscriber.assertValueCount(1)

        expect(TEAM_NAME, { store.teams[0].name })
        expect(USER_ID, { store.authorizedUsers[0].id })
    }
}

package info.izumin.android.sunazuri.infrastructure.repository.source.oauth

import com.github.gfx.static_gson.StaticGsonTypeAdapterFactory
import com.google.gson.GsonBuilder
import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.api.OauthApi
import info.izumin.android.sunazuri.infrastructure.api.UsersApi
import info.izumin.android.sunazuri.infrastructure.cache.LoginCache
import info.izumin.android.sunazuri.infrastructure.cache.LoginCacheImpl
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams
import info.izumin.android.sunazuri.infrastructure.mock.api.MockOauthApi
import info.izumin.android.sunazuri.infrastructure.mock.api.MockUsersApi
import info.izumin.android.sunazuri.infrastructure.mock.util.MockEncryptor
import info.izumin.android.sunazuri.infrastructure.pref.PrefsProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import rx.observers.TestSubscriber
import kotlin.test.expect

/**
 * Created by izumin on 5/21/2016 AD.
 */

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class OauthRemoteDataSourceTest {

    val oauthParams = OauthParams(
            "https://example.com/",
            "client_id",
            "client_secret",
            "http://example.com",
            "read+write",
            "token",
            "/oauth/authorize",
            "authorization_code"
    )

    val context = RuntimeEnvironment.application.applicationContext
    val encryptor = MockEncryptor(context)

    val gson = GsonBuilder()
            .registerTypeAdapterFactory(StaticGsonTypeAdapterFactory.newInstance())
            .create()
    val retrofit = Retrofit.Builder()
            .baseUrl(oauthParams.endpoint)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    val mockRetrofit = MockRetrofit.Builder(retrofit).build()

    val usersApi = MockUsersApi(mockRetrofit.create(UsersApi::class.java))
    val oauthApi = MockOauthApi(mockRetrofit.create(OauthApi::class.java))

    lateinit var orma: OrmaProvider
    lateinit var accessTokenDao: AccessTokenDao

    lateinit var prefProvider: PrefsProvider
    lateinit var loginCache: LoginCache

    lateinit var dataSource: OauthDataSource

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        accessTokenDao = AccessTokenDao(orma)
        prefProvider = PrefsProvider(context)
        loginCache = LoginCacheImpl(prefProvider)

        dataSource = OauthRemoteDataSource(usersApi, oauthApi, oauthParams, accessTokenDao, encryptor, loginCache)
    }

    @Test
    fun testGetToken() {
        val subscriber = TestSubscriber<AccessTokenEntity>()
        dataSource.getToken("testcode").subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        subscriber.onNextEvents[0].accessToken = oauthApi.TOKEN.accessToken
        subscriber.onNextEvents[0].user.id = usersApi.USER_ID

        expect(oauthApi.TOKEN.accessToken, {
            encryptor.decrypt(orma.db.selectFromAccessTokenEntity().userEq(usersApi.USER_ID).value().accessToken)
        })
        expect(usersApi.USER.screenName, {
            orma.db.selectFromAuthorizedUserEntity().idEq(usersApi.USER_ID).value().screenName
        })
    }
}

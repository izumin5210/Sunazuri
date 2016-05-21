package info.izumin.android.sunazuri.infrastructure.repository.source.oauth

import com.github.gfx.static_gson.StaticGsonTypeAdapterFactory
import com.google.gson.GsonBuilder
import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.api.OauthApi
import info.izumin.android.sunazuri.infrastructure.api.UsersApi
import info.izumin.android.sunazuri.infrastructure.dao.AccessTokenDao
import info.izumin.android.sunazuri.infrastructure.dao.OrmaProvider
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.OauthParams
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase
import info.izumin.android.sunazuri.infrastructure.mock.api.MockOauthApi
import info.izumin.android.sunazuri.infrastructure.mock.api.MockUsersApi
import info.izumin.android.sunazuri.infrastructure.mock.dao.MockOrmaProvider
import info.izumin.android.sunazuri.infrastructure.mock.util.MockEncryptor
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

    lateinit var db: OrmaDatabase
    lateinit var orma: OrmaProvider
    lateinit var accessTokenDao: AccessTokenDao

    lateinit var dataSource: OauthDataSource

    @Before
    fun setUp() {
        db = OrmaDatabase.builder(context).name(null).build()
        orma = MockOrmaProvider(context, db)
        accessTokenDao = AccessTokenDao(orma)

        dataSource = OauthRemoteDataSource(usersApi, oauthApi, oauthParams, accessTokenDao, encryptor)
    }

    @Test
    fun testGetToken() {
        val subscriber = TestSubscriber<AccessTokenEntity>()
        dataSource.getToken("testcode").subscribe(subscriber)

        subscriber.awaitTerminalEvent()
        subscriber.assertNoErrors()
        subscriber.onNextEvents[0].accessToken = oauthApi.TOKEN.accessToken
        subscriber.onNextEvents[0].user.id = usersApi.USER.id

        expect(oauthApi.TOKEN.accessToken, {
            encryptor.decrypt(db.selectFromAccessTokenEntity().userEq(usersApi.USER.id).value().accessToken)
        })
        expect(usersApi.USER.screenName, {
            db.selectFromAuthorizedUserEntity().idEq(usersApi.USER.id).value().screenName
        })
    }
}

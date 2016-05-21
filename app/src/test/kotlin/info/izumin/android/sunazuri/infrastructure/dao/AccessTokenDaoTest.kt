package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.domain.entity.AccessToken
import info.izumin.android.sunazuri.domain.entity.OrmaDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import kotlin.test.expect

/**
 * Created by izumin on 5/21/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class AccessTokenDaoTest {

    val TOKEN = AccessToken()

    init {
        TOKEN.accessToken   = "testtoken"
        TOKEN.scope         = "read+write"
        TOKEN.tokenType     = "bearer"
    }

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var orma: OrmaDatabase
    lateinit var dao: AccessTokenDao

    @Before
    fun setUp() {
        orma = OrmaDatabase.builder(context).name(null).build()
        dao = AccessTokenDao(orma)
    }

    @Test
    fun testInsert() {
        dao.insert(TOKEN)

        expect(1, { orma.selectFromAccessToken().count() })
    }
}
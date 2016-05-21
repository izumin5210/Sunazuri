package info.izumin.android.sunazuri.infrastructure.dao

import android.content.Context
import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase
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

    val TOKEN = AccessTokenEntity()

    init {
        TOKEN.accessToken   = "testtoken"
        TOKEN.scope         = "read+write"
        TOKEN.tokenType     = "bearer"
    }

    class TestOrmaProvider(context: Context, override val db: OrmaDatabase): OrmaProvider(context = context) {
    }

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var db: OrmaDatabase
    lateinit var orma: OrmaProvider
    lateinit var dao: AccessTokenDao

    @Before
    fun setUp() {
        db = OrmaDatabase.builder(context).name(null).build()
        orma = TestOrmaProvider(context, db)
        dao = AccessTokenDao(orma)
    }

    @Test
    fun testInsert() {
        dao.insert(TOKEN)

        expect(1, { db.selectFromAccessTokenEntity().count() })
    }
}
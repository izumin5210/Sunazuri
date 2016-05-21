package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.BuildConfig
import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.OrmaDatabase
import info.izumin.android.sunazuri.infrastructure.mock.dao.MockOrmaProvider
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*
import kotlin.test.expect

/**
 * Created by izumin on 5/21/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(21))
class AccessTokenDaoTest {

    val TOKEN = AccessTokenEntity()
    val USER = AuthorizedUserEntity()

    init {
        USER.id         = 1
        USER.name       = "test user"
        USER.screenName = "test_user"
        USER.createdAt  = Date()
        USER.updatedAt  = Date()
        USER.icon       = "http://example.com/test_user.png"
        USER.email      = "test@example.com"

        TOKEN.accessToken   = "testtoken"
        TOKEN.scope         = "read+write"
        TOKEN.tokenType     = "bearer"
        TOKEN.user          = USER
    }


    val context = RuntimeEnvironment.application.applicationContext

    lateinit var db: OrmaDatabase
    lateinit var orma: OrmaProvider
    lateinit var dao: AccessTokenDao

    @Before
    fun setUp() {
        db = OrmaDatabase.builder(context).name(null).build()
        orma = MockOrmaProvider(context, db)
        dao = AccessTokenDao(orma)
    }

    @Test
    fun testInsert() {
        dao.upsert(TOKEN)

        expect(1, { db.selectFromAuthorizedUserEntity().count() })
        expect(1, { db.selectFromAccessTokenEntity().count() })
        expect(1, { db.selectFromAccessTokenEntity().userEq(USER.id).count() })
    }

    @Test
    fun testUpdate() {
        dao.upsert(TOKEN)
        TOKEN.accessToken = "newtesttoken"
        USER.name = "new test user"
        dao.upsert(TOKEN)

        expect(1, { db.selectFromAuthorizedUserEntity().count() })
        expect(1, { db.selectFromAccessTokenEntity().count() })
        expect("newtesttoken", { db.selectFromAccessTokenEntity().userEq(USER.id).value().accessToken })
        expect("new test user", { db.selectFromAuthorizedUserEntity().idEq(USER.id).value().name })
    }
}
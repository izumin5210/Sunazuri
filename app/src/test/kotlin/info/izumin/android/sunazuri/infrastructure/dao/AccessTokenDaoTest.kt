package info.izumin.android.sunazuri.infrastructure.dao

import info.izumin.android.sunazuri.infrastructure.entity.AccessTokenEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.expect

/**
 * Created by izumin on 5/21/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
class AccessTokenDaoTest {

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var orma: OrmaProvider
    lateinit var dao: AccessTokenDao

    lateinit var token: AccessTokenEntity

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        dao = AccessTokenDao(orma)

        token = DataFactory.createAccessTokenEntity()
    }

    @Test
    fun testInsert() {
        dao.upsert(token)

        expect(1, { orma.db.selectFromAuthorizedUserEntity().count() })
        expect(1, { orma.db.selectFromAccessTokenEntity().count() })
        expect(1, { orma.db.selectFromAccessTokenEntity().userEq(token.user).count() })
    }

    @Test
    fun testUpdate() {
        dao.upsert(token)
        token.accessToken = "newtesttoken"
        token.user.name = "new test user"
        dao.upsert(token)

        expect(1, { orma.db.selectFromAuthorizedUserEntity().count() })
        expect(1, { orma.db.selectFromAccessTokenEntity().count() })
        expect("newtesttoken", { orma.db.selectFromAccessTokenEntity().userEq(token.user).value().accessToken })
        expect("new test user", { orma.db.selectFromAuthorizedUserEntity().idEq(token.user.id).value().name })
    }
}
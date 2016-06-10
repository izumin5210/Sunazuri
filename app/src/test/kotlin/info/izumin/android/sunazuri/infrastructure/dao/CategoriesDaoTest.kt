package info.izumin.android.sunazuri.infrastructure.dao;

import info.izumin.android.sunazuri.infrastructure.entity.AuthorizedUserEntity
import info.izumin.android.sunazuri.infrastructure.entity.TeamEntity
import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import kotlin.test.assertEquals

/**
 * Created by izumin on 6/10/2016 AD.
 */
@RunWith(RobolectricGradleTestRunner::class)
class CategoriesDaoTest {

    val context = RuntimeEnvironment.application.applicationContext

    lateinit var orma: OrmaProvider
    lateinit var dao: CategoriesDao

    lateinit var user: AuthorizedUserEntity
    lateinit var team: TeamEntity

    @Before
    fun setUp() {
        orma = OrmaProvider(context, null)
        dao = CategoriesDao(orma)

        user = DataFactory.createAuthorizedUserEntity()
        team = DataFactory.createTeamEntity()
        team.user = user

        orma.db.relationOfAuthorizedUserEntity().inserter().execute(user)
        orma.db.relationOfTeamEntity().inserter().execute(team)
    }

    @Test
    fun testInsert() {
        val entity = DataFactory.createCategoryEntity()
        dao.insert(listOf(entity), team)

        assertEquals(1, orma.db.selectFromCategoryEntity().count())
        assertEquals(entity.path, orma.db.selectFromCategoryEntity().first().path)
    }

    @Test
    fun testInsertWithUpdate() {
        val entity = DataFactory.createCategoryEntity()
        dao.insert(listOf(entity), team)
        entity.path = "DailyReport/2016/05/25"
        dao.insert(listOf(entity), team)

        assertEquals(1, orma.db.selectFromCategoryEntity().count())
        assertEquals("DailyReport/2016/05/25", orma.db.selectFromCategoryEntity().first().path)
    }
}

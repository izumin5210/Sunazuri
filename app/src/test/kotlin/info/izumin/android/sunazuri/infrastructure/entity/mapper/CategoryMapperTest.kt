package info.izumin.android.sunazuri.infrastructure.entity.mapper;

import info.izumin.android.sunazuri.infrastructure.mock.DataFactory
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by izumin on 6/10/2016 AD.
 */
class CategoryMapperTest {

    val mapper = CategoryMapper()


    @Test
    fun testTransformFromApiResponse() {
        val res = DataFactory.createCategoryApiResponse()
        val entities = mapper.transform(res)

        assertEquals(6, entities.size)
        assertEquals("DailyReport",             entities[0].path)
        assertEquals("DailyReport/2016",        entities[1].path)
        assertEquals("DailyReport/2016/02",     entities[2].path)
        assertEquals("DailyReport/2016/02/04",  entities[3].path)
        assertEquals("DailyReport/2016/02/20",  entities[4].path)
        assertEquals("Note",                    entities[5].path)
        assertEquals(13, entities[5].count)
    }
}
